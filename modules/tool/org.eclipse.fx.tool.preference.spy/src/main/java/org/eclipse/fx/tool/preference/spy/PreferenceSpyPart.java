/*******************************************************************************
 * Copyright (c) Carl Zeiss Meditec AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thomas Fahrmeyer (lauritziu_ii@yahoo.de) - initial API and implementation
*/
package org.eclipse.fx.tool.preference.spy;

import org.eclipse.fx.tool.preference.spy.internal.model.PreferenceSpyViewModel;
import org.eclipse.fx.tool.preference.spy.internal.model.PreferenceTracker;
import org.eclipse.fx.tool.preference.spy.internal.view.PreferenceSpyView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import javax.annotation.PostConstruct;

/**
 * Model part contribution for preference spy 
 */
public class PreferenceSpyPart {

	/**
	 * Prepares the PreferenceSpyPart	
	 * 	- creates ViewModel
	 *  - puts the ViewModel to part context (so that other UI elements can access it)
	 *  - creates View
	 *  - gets UI Control from View and adds to parentPane 
	 * 
	 * @param preferenceSpyViewModel
	 * @param preferenceSpyView
	 * @param parentPane
	 * @param part
	 * @param preferenceTracker
	 */
	@PostConstruct
	public void postConstruct(PreferenceSpyView preferenceSpyView,
			BorderPane parentPane, MPart part, PreferenceTracker preferenceTracker ) {

        part.getContext().set(PreferenceSpyViewModel.class.getName(), preferenceSpyView.getModel() );
		
		Node control = preferenceSpyView.getGui();
		
		parentPane.setCenter( control );
	}

}
