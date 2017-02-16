/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2017 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.controls.txn;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.ui.UIUtils;

public class TransactionLogDialog extends Dialog {

    private static final String DIALOG_ID = "DBeaver.TransactionLogDialog";//$NON-NLS-1$

    private final DBCExecutionContext context;

    public TransactionLogDialog(Shell parentShell, DBCExecutionContext context)
    {
        super(parentShell);
        this.context = context;
    }

    @Override
    protected boolean isResizable() {
    	return true;
    }

    protected IDialogSettings getDialogBoundsSettings()
    {
        return UIUtils.getDialogSettings(DIALOG_ID);
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        getShell().setText("Transaction log [" + context.getContextName() + "]");

        Composite composite = (Composite) super.createDialogArea(parent);

        return parent;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    public static void showDialog(Shell shell, DBCExecutionContext executionContext) {
        if (executionContext != null) {
            final TransactionLogDialog dialog = new TransactionLogDialog(shell, executionContext);
            dialog.open();
        } else {
            UIUtils.showErrorDialog(
                shell,
                "Not connected",
                "Transaction log is not available.\nConnect to a database.");
        }
    }
}
