/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.vainruling.core.web.admin.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import net.vpc.app.vainruling.api.CorePlugin;
import net.vpc.app.vainruling.api.model.AppRightName;
import net.vpc.app.vainruling.api.model.AppUser;
import net.vpc.app.vainruling.api.util.VrHelper;
import net.vpc.common.strings.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vpc
 */
@Component
@ManagedBean
@Scope("session")
public class UpdateProfileUsersActionCtrl {

    private static final Logger log = Logger.getLogger(UpdateProfileUsersActionCtrl.class.getName());
    @Autowired
    private CorePlugin core;
    private Model model = new Model();

    public static class Config {

        private String profile;

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

    }

    public void openDialog(String config) {
        openDialog(VrHelper.parseJSONObject(config, Config.class));
    }

    public void openDialog(Config config) {
        if (config == null) {
            config = new Config();
        }
        if (!StringUtils.isEmpty(config.profile)) {
            int profileId = Integer.parseInt(config.profile);
            List<AppUser>[] list = core.findProfileUsersDualList(profileId);
            Comparator<AppUser> comp = new Comparator<AppUser>() {
                @Override
                public int compare(AppUser o1, AppUser o2) {
                    return o1.getLogin().compareTo(o2.getLogin());
                }
            };
            Collections.sort(list[0], comp);
            Collections.sort(list[1],comp);
            DualListModel<AppUser> v = new DualListModel<AppUser>();
            v.setSource(list[1]);
            v.setTarget(list[0]);
            getModel().setValues(v);
            getModel().setProfileId(profileId);
        }else{
            DualListModel<AppUser> v = new DualListModel<AppUser>();
            getModel().setValues(v);
            getModel().setProfileId(-1);
        }
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/modules/admin/updateProfileUsersDialog", options, null);

    }

  

    public void save() {
        List t = getModel().getValues().getTarget();
        List<String> users=new ArrayList<>();
        for (Object tt : t) {
             String name=null;
            if(tt instanceof AppUser){
                name=((AppUser)tt).getLogin();
            }else{
                name=((String)tt);
            }
            users.add(name);
        }
        core.setProfileUsers(getModel().getProfileId(), users);
        fireEventExtraDialogClosed();
    }

    public void onChange() {

    }

    public void fireEventExtraDialogClosed() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public static class Model {

        private int profileId;
        private DualListModel<AppUser> values;

        public DualListModel<AppUser> getValues() {
            return values;
        }

        public void setValues(DualListModel<AppUser> values) {
            this.values = values;
        }

        public int getProfileId() {
            return profileId;
        }

        public void setProfileId(int profileId) {
            this.profileId = profileId;
        }
        

    }

    public Model getModel() {
        return model;
    }

}