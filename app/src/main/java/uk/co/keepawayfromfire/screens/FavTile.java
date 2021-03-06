package uk.co.keepawayfromfire.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.quicksettings.TileService;

/**
 * Created by cj on 05/01/17.
 */

public class FavTile extends TileService {

    @Override
    public void onClick() {
        super.onClick();

        if (isLocked())
            return;

        SharedPreferences prefs = getSharedPreferences("prefs_tile", MODE_PRIVATE);

        String type = prefs.getString(ShortcutActivity.INTENT_TYPE,
                ShortcutActivity.INTENT_TYPE_PACKAGES);

        if (type.equals(ShortcutActivity.INTENT_TYPE_INTENTS))
            return;

        String pkg1 = prefs.getString(ShortcutActivity.INTENT_EXTRA_PACKAGE_1, null);
        String pkg2 = prefs.getString(ShortcutActivity.INTENT_EXTRA_PACKAGE_2, null);

        if (pkg1 == null || pkg2 == null || pkg1.isEmpty() || pkg2.isEmpty())
            startActivityAndCollapse(new Intent(this, MainActivity.class));
        else
            startActivityAndCollapse(ShortcutActivity.createShortcutIntent(this, pkg1, pkg2));
    }
}
