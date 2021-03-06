/*
 * This file is part of the Illarion Client.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Client.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.client.graphics;

import org.newdawn.slick.GameContainer;

/**
 * Interface for a object that can be rendered on the screen.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
public interface DisplayItem extends Drawable {
    /**
     * Get the z order of the item. This order is used to sort the display items
     * in a proper order to be rendered.
     *
     * @return the z layer coordinate of the display item
     */
    int getZOrder();

    /**
     * Remove object from display list.
     */
    void hide();

    /**
     * Show object by adding it to the display list.
     */
    void show();

    /**
     * Handle the event.
     *
     * @param c
     * @param delta
     * @param event the event to process  @return {@code true} in case the event was processed,
     *              false in case it was not
     */
    boolean processEvent(final GameContainer c, final int delta, MapInteractionEvent event);
}
