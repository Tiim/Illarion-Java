/*
 * This file is part of the Illarion Mapeditor.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Mapeditor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Mapeditor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Mapeditor.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.mapedit.tools;

import illarion.mapedit.Lang;
import illarion.mapedit.data.Map;
import illarion.mapedit.data.MapTile;
import illarion.mapedit.history.MusicIDChangedAction;
import illarion.mapedit.tools.panel.MusicPanel;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;

/**
 * TODO: Bigger brush
 * TODO: Play music
 * TODO: Load ids from table
 *
 * @author Tim
 */
public class MusicTool extends AbstractTool {

    private final MusicPanel panel;


    public MusicTool() {
        panel = new MusicPanel();
    }

    @Override
    public void clickedAt(final int x, final int y, final Map map) {
        final int musicID = panel.getMusicID();
        System.out.println(x + "  " + y);
        if (map.getTileAt(x, y).getMusicID() == musicID) {
            return;
        }
        getHistory().addEntry(new MusicIDChangedAction(x, y, map.getTileAt(x, y).getMusicID(), musicID, map));
        map.setTileAt(x, y, MapTile.MapTileFactory.setMusicId(musicID, map.getTileAt(x, y)));
    }

    @Override
    public String getLocalizedName() {
        return Lang.getMsg("tools.MusicTool");
    }

    @Override
    public ResizableIcon getToolIcon() {
        return null;
    }

    @Override
    public JPanel getSettingsPanel() {
        return panel;
    }
}
