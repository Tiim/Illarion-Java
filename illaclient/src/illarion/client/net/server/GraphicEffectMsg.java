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
package illarion.client.net.server;

import illarion.client.net.CommandList;
import illarion.client.net.annotations.ReplyMessage;
import illarion.client.world.MapTile;
import illarion.client.world.World;
import illarion.common.net.NetCommReader;
import illarion.common.types.Location;

import java.io.IOException;

/**
 * Servermessage: Graphic effect ( {@link CommandList#MSG_GRAPHIC_FX}).
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
@ReplyMessage(replyId = CommandList.MSG_GRAPHIC_FX)
public final class GraphicEffectMsg
        extends AbstractReply {
    /**
     * ID of the effect that shall be shown.
     */
    private int effectId;

    /**
     * The location the effect occurs on.
     */
    private transient Location loc;

    /**
     * Decode the effect data the receiver got and prepare it for the execution.
     *
     * @param reader the receiver that got the data from the server that needs to be decoded
     * @throws java.io.IOException thrown in case there was not enough data received to decode the full message
     */
    @Override
    public void decode(final NetCommReader reader)
            throws IOException {
        loc = decodeLocation(reader);
        effectId = reader.readUShort();
    }

    /**
     * Execute the effect message and send the decoded data to the rest of the client.
     *
     * @return true if the execution is done, false if it shall be called again
     */
    @Override
    public boolean executeUpdate() {
        final MapTile tile = World.getMap().getMapAt(loc);
        if (tile != null) {
            tile.showEffect(effectId);
        }
        return true;
    }

    /**
     * Get the data of this effect message as string.
     *
     * @return the string that contains the values that were decoded for this message
     */
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return toString("Graphic Effect: " + effectId);
    }
}
