/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation, BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   IBM Corporation - initial API
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Util;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ControlEditor {
	private Composite parent;
	private Control editor;
	
	public int horizontalAlignment = SWT.CENTER;
	public boolean grabHorizontal = false;
	public int minimumWidth = 0;
	public int verticalAlignment = SWT.CENTER;
	public boolean grabVertical = false;
	public int minimumHeight = 0;
	
	public ControlEditor (Composite parent) {
		this.parent = parent;
	}
	
	public Control getEditor() {
		return editor;
	}
	
	public void setEditor(Control editor) {
		this.editor = editor;
	}
	
	public void layout() {
		Util.logNotImplemented();
	}
	
	public void dispose () {
		
	}
}
