/*
 * This file is part of the Illarion easyNPC Editor.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion easyNPC Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion easyNPC Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion easyNPC Editor.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.easynpc.parser.talk.conditions;

import illarion.easynpc.Lang;
import illarion.easynpc.data.CompareOperators;
import illarion.easynpc.parsed.talk.AdvancedNumber;
import illarion.easynpc.parsed.talk.TalkCondition;
import illarion.easynpc.parsed.talk.conditions.ConditionQueststatus;
import illarion.easynpc.parser.talk.AdvNumber;
import illarion.easynpc.parser.talk.ConditionParser;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a quest status condition. Its able to parse a quest status value out of the NPC condition line.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class Queststatus extends ConditionParser {
    /**
     * This pattern is used to find the quest status operation in the condition properly.
     */
    @SuppressWarnings("nls")
    private static final Pattern QUESTSTATUS_FIND = Pattern.compile("\\s*queststatus\\s*\\(\\s*(\\d+)\\s*\\)\\s*" +
            "([=~!<>]{1,2})\\s*" + AdvNumber.ADV_NUMBER_REGEXP + "\\s*,\\s*", Pattern.CASE_INSENSITIVE);

    /**
     * Extract a condition from the working string.
     */
    @Override
    @SuppressWarnings("nls")
    public TalkCondition extract() {
        if (getNewLine() == null) {
            throw new IllegalStateException("Can't extract if no state set.");
        }

        final Matcher stringMatcher = QUESTSTATUS_FIND.matcher(getNewLine());
        if (stringMatcher.find()) {
            final String comperator = stringMatcher.group(2);
            final AdvancedNumber targetValue =
                    AdvNumber.getNumber(stringMatcher.group(3));
            final int questID = Integer.parseInt(stringMatcher.group(1));

            setLine(stringMatcher.replaceFirst(""));

            if (targetValue == null) {
                reportError(String.format(Lang.getMsg(getClass(), "number"),
                        stringMatcher.group(3), stringMatcher.group(0)));
                return extract();
            }

            CompareOperators operator = null;
            for (final CompareOperators op : CompareOperators.values()) {
                if (op.getRegexpPattern().matcher(comperator).matches()) {
                    operator = op;
                    break;
                }
            }

            if (operator == null) {
                reportError(String.format(Lang.getMsg(getClass(), "operator"),
                        comperator, stringMatcher.group(0)));
                return extract();
            }

            return new ConditionQueststatus(questID, operator, targetValue);
        }

        return null;
    }

    @Override
    public String getDescription() {
        return Lang.getMsg(getClass(), "Docu.description"); //$NON-NLS-1$
    }

    @Override
    public String getExample() {
        return Lang.getMsg(getClass(), "Docu.example"); //$NON-NLS-1$
    }

    @Override
    public String getSyntax() {
        return Lang.getMsg(getClass(), "Docu.syntax"); //$NON-NLS-1$
    }

    @Override
    public String getTitle() {
        return Lang.getMsg(getClass(), "Docu.title"); //$NON-NLS-1$
    }

    @Override
    public void enlistHighlightedWords(final TokenMap map) {
        map.put("queststatus", Token.RESERVED_WORD);
    }
}
