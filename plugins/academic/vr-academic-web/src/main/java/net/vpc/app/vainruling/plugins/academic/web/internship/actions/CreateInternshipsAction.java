/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.vpc.app.vainruling.plugins.academic.web.internship.actions;

import net.vpc.app.vainruling.core.service.VrApp;
import net.vpc.app.vainruling.core.service.obj.EntityAction;
import net.vpc.app.vainruling.core.web.obj.ActionDialog;
import net.vpc.app.vainruling.core.web.obj.ActionDialogResult;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.current.AcademicInternship;
import net.vpc.upa.AccessMode;

import java.util.List;

/**
 * @author taha.bensalah@gmail.com
 */
@EntityAction(entityType = AcademicInternship.class,
        actionLabel = "generer", actionStyle = "fa-envelope-o",
        dialog = true
)
public class CreateInternshipsAction implements ActionDialog {

    @Override
    public void openDialog(String actionId, List<String> itemIds) {
//        Object co = VrApp.getBean(ObjCtrl.class).getCurrentEntityObject();
//        if (co == null) {
//            return;
//        }
        VrApp.getBean(CreateInternshipsActionCtrl.class).openDialog();
    }

    @Override
    public boolean isEnabled(String actionId, Class entityType, AccessMode mode, Object value) {
        return mode == AccessMode.READ;
    }

    @Override
    public ActionDialogResult invoke(String actionId, Class entityType, Object obj, List<String> selectedIdStrings, Object[] args) {
        //do nothing!
        return ActionDialogResult.VOID;
    }
}
