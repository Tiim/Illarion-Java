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
import illarion.client.world.World;
import illarion.common.net.NetCommReader;
import illarion.common.types.Location;

import java.io.IOException;

/**
 * Servermessage: Look at description of a tile ( {@link CommandList#MSG_LOOKAT_TILE}).
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
@ReplyMessage(replyId = CommandList.MSG_LOOKAT_TILE)
public final class LookAtTileMsg
        extends AbstractReply {
    /**
     * The location of the tile on the server map.
     */
    private transient Location loc;

    /**
     * The look at text for the tile.
     */
    private String text;

    /**
     * Decode the tile look at text data the receiver got and prepare it for the execution.
     *
     * @param reader the receiver that got the data from the server that needs to be decoded
     * @throws IOException thrown in case there was not enough data received to decode the full message
     */
    @Override
    public void decode(final NetCommReader reader)
            throws IOException {
        loc = decodeLocation(reader);
        text = reader.readString();
    }

    /**
     * Execute the tile look at text message and send the decoded data to the rest of the client.
     *
     * @return true if the execution is done, false if it shall be called again
     */
    @Override
    public boolean executeUpdate() {
        World.getMapDisplay().lookAt(loc.getDcX(), loc.getDcY(), text);
        //        GUI.getInstance().getChatText()
        //            .showText(text, null, loc, ChatHandler.SpeechMode.normal);

        // for testing lookat only
        World.getChatHandler().handleMessage(text, loc);

        return true;
    }

    /**
     * Get the data of this tile look at text message as string.
     *
     * @return the string that contains the values that were decoded for this message
     */
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return toString("Location: " + loc.toString() + " Message: " + text);
    }
}
