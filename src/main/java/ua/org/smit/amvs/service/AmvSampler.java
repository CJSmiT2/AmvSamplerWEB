package ua.org.smit.amvs.service;

import java.io.File;
import java.util.List;
import ua.org.smit.commontlx.filesystem.FolderCms;

public class AmvSampler {

    private final FolderCms groupsFolder;
    private final FolderCms root;

    public AmvSampler(String folder) {
        root = new FolderCms(folder);
        groupsFolder = new FolderCms(folder + File.separator + "groups_by_titles");
    }

    public List<String> getGroups() {
        return groupsFolder.getFilesNames();
    }

    public Group getSamples(String groupName) {
        for (String name : getGroups()) {
            if (name.equals(groupName)) {
                return new Group(name, root + File.separator + "complete", groupsFolder);
            }
        }
        throw new RuntimeException("Cant find group '" + groupName + "'");
    }
}
