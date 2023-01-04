package backup_actions;

import database.Session;
import pages.Page;

public class CommandAction {
    private SnapshotAction backup;

    public void makeBackUp (Page page) {
        backup = page.createSnapshot();
    }

    public void undo(Page page) {
        if (backup != null) {
            backup.restore(page);
        }
    }
}
