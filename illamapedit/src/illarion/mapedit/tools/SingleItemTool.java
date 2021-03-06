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
import illarion.mapedit.data.MapItem;
import illarion.mapedit.history.ItemPlacedAction;
import illarion.mapedit.resource.ItemImg;
import illarion.mapedit.tools.panel.SingleItemPanel;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.util.List;

/**
 * @author Tim
 */
public class SingleItemTool extends AbstractTool {

    public final SingleItemPanel panel;

    public SingleItemTool() {
        panel = new SingleItemPanel();
    }

    @Override
    public void clickedAt(final int x, final int y, final Map map) {
        if (!map.contains(x, y)) {
            return;
        }
        final ItemImg item = getManager().getSelectedItem();
        if (item != null) {
            final List<MapItem> items = map.getTileAt(x, y).getMapItems();
            final MapItem i = new MapItem(item.getItemId(), "", 0);
            if (!items.contains(i)) {
                getHistory().addEntry(new ItemPlacedAction(x, y, null, i, map));
                items.add(i);
            }
        }
    }

    @Override
    public String getLocalizedName() {
        return Lang.getMsg("tools.SingleItemTool");
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
