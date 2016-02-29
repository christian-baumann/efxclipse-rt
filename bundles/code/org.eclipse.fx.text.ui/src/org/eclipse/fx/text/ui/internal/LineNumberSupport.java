package org.eclipse.fx.text.ui.internal;

import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.fx.core.Subscription;
import org.eclipse.fx.ui.controls.styledtext.StyledTextArea;
import org.eclipse.fx.ui.controls.styledtext.model.Annotation;
import org.eclipse.fx.ui.controls.styledtext.model.AnnotationProvider;
import org.eclipse.fx.ui.controls.styledtext.model.LineRulerAnnotationPresenter;

import com.google.common.collect.RangeSet;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class LineNumberSupport {

	private StyledTextArea control;

	public LineNumberSupport(StyledTextArea control) {
		this.control = control;
	}


	public class LineNrAnnotation implements Annotation {

		private final int nr;

		public LineNrAnnotation(int nr) {
			this.nr = nr;
		}
		public int getNr() {
			return nr;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + nr;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LineNrAnnotation other = (LineNrAnnotation) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (nr != other.nr)
				return false;
			return true;
		}
		private LineNumberSupport getOuterType() {
			return LineNumberSupport.this;
		}


	}

	public class LineNrAnnotationPresenter implements LineRulerAnnotationPresenter {

		@Override
		public LayoutHint getLayoutHint() {
			return LayoutHint.ALIGN_RIGHT;
		}

		@Override
		public boolean isApplicable(Annotation annotation) {
			return annotation instanceof LineNrAnnotation;
		}

		@Override
		public Node createNode() {
			Text node = new Text();
			node.getStyleClass().add("line-ruler-text");
			return node;
		}

		private DoubleProperty w = new SimpleDoubleProperty(16);

		@Override
		public DoubleProperty getWidth() {
			return w;
		}

		@Override
		public int getOrder() {
			return 10000;
		}

		@Override
		public boolean isVisible(Set<Annotation> annotation) {
			return true;
		}

		@Override
		public void updateNode(Node node, Set<Annotation> annotation) {
			Text n = (Text) node;
			annotation.stream().findFirst().ifPresent(m->{
				int nr = ((LineNrAnnotation)m).getNr();
				n.setText("" + nr);
			});
		}



	}

	public class LineNrAnnotationProvider implements AnnotationProvider {

		@Override
		public Set<? extends Annotation> computeAnnotations(int index) {
			return Collections.singleton(new LineNrAnnotation(index + 1));
		}

		@Override
		public Subscription registerChangeListener(Consumer<RangeSet<Integer>> onChange) {
			return new Subscription() {
				@Override
				public void dispose() {

				}
			};
		}

	}

	public void install() {
		LineNrAnnotationPresenter presenter = new LineNrAnnotationPresenter();
		this.control.getAnnotationProvider().add(new LineNrAnnotationProvider());
		this.control.getAnnotationPresenter().add(presenter);

		DoubleBinding width = Bindings.createDoubleBinding(()->Integer.toString(this.control.lineCountProperty().get()).length() * 8d, this.control.lineCountProperty());
		presenter.w.bind(width);
	}
}