/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.vpc.app.vainruling.plugins.academic.web.internship.actions;

import net.vpc.app.vainruling.core.service.CorePlugin;
import net.vpc.app.vainruling.core.service.VrApp;
import net.vpc.app.vainruling.core.web.obj.PropertyView;
import net.vpc.app.vainruling.core.web.obj.PropertyViewManager;
import net.vpc.app.vainruling.core.web.obj.defaultimpl.FieldPropertyView;
import net.vpc.app.vainruling.plugins.academic.service.AcademicPlugin;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.config.AcademicInternshipStatus;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.config.AcademicInternshipVariant;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.current.AcademicInternship;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.current.AcademicInternshipBoard;
import net.vpc.app.vainruling.plugins.academic.service.model.internship.current.AcademicInternshipGroup;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author taha.bensalah@gmail.com
 */
@Component
@Scope("session")
public class CreateInternshipsActionCtrl {

    private static final Logger log = Logger.getLogger(CreateInternshipsActionCtrl.class.getName());
    @Autowired
    private CorePlugin core;
    private Model model = new Model();
    @Autowired
    private PropertyViewManager propertyViewManager;

    public void openDialog() {
        getModel().setDisabled(false);

        initModel();

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/modules/academic/internship/create-internships-dialog", options, null);

    }

    private void initModel(){
        getModel().setMessage("");
        getModel().setProfile("");
        AcademicInternship internship = new AcademicInternship();
        getModel().setInternship(internship);
        getModel().setBoard((FieldPropertyView) propertyViewManager.createPropertyView(AcademicInternshipBoard.class));
        getModel().setGroup((FieldPropertyView) propertyViewManager.createPropertyView(AcademicInternshipGroup.class));
        getModel().setInternshipVariant((FieldPropertyView) propertyViewManager.createPropertyView(AcademicInternshipVariant.class));
        getModel().setStatus((FieldPropertyView) propertyViewManager.createPropertyView(AcademicInternshipStatus.class));
        getModel().getInternship().setName("Ecrire le titre de ton PFE ici");
        getModel().getInternship().setDescription("Ecrire une description longue de ton PFE ici. N'oublie pas par contre d'attacher le PDF de cahier des charges de ton  PFE. Tous les champs doivent etre remplis correctement.");
        if(getModel().getInternshipVariant().getValues().size()>0) {
            getModel().getInternshipVariant().setSelectedItem(getModel().getInternshipVariant().getValues().get(0).getId());
        }
        if(getModel().getStatus().getValues().size()>0) {
            getModel().getStatus().setSelectedItem(getModel().getStatus().getValues().get(0).getId());
        }
        //        getModel().getInternship().setInternshipStatus();
    }

    public void save() {
        getModel().getInternship().setMainGroup((AcademicInternshipGroup) getModel().getGroup().getObjectValue());
        getModel().getInternship().setBoard((AcademicInternshipBoard) getModel().getBoard().getObjectValue());
        getModel().getInternship().setInternshipStatus((AcademicInternshipStatus) getModel().getStatus().getObjectValue());
        getModel().getInternship().setInternshipVariant((AcademicInternshipVariant) getModel().getInternshipVariant().getObjectValue());
        if (getModel().getInternship().getBoard() == null) {
            getModel().setMessage("Merci de preciser le Comité");
        } else if (getModel().getInternship().getInternshipStatus() == null) {
            getModel().setMessage("Merci de preciser l'étape");
        } else {
            int count=VrApp.getBean(AcademicPlugin.class).generateInternships(getModel().getInternship(), getModel().getProfile());
            if(count>0) {
                fireEventExtraDialogClosed();
            }else{
                getModel().setMessage("Aucun Stage généré");
            }
        }
    }

    public void onChange() {

    }

    public void fireEventExtraDialogClosed() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public Model getModel() {
        return model;
    }

    public static class Model {

        private String profile;
        private boolean disabled;
        private String message;
        private FieldPropertyView group;
        private FieldPropertyView board;
        private FieldPropertyView status;
        private FieldPropertyView internshipVariant;
        private AcademicInternship internship;

        public FieldPropertyView getGroup() {
            return group;
        }

        public void setGroup(FieldPropertyView group) {
            this.group = group;
        }

        public FieldPropertyView getBoard() {
            return board;
        }

        public void setBoard(FieldPropertyView board) {
            this.board = board;
        }

        public FieldPropertyView getStatus() {
            return status;
        }

        public void setStatus(FieldPropertyView status) {
            this.status = status;
        }

        public FieldPropertyView getInternshipVariant() {
            return internshipVariant;
        }

        public void setInternshipVariant(FieldPropertyView internshipVariant) {
            this.internshipVariant = internshipVariant;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public AcademicInternship getInternship() {
            return internship;
        }

        public void setInternship(AcademicInternship internship) {
            this.internship = internship;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
