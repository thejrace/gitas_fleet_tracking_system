/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events;

import interfaces.Postable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RunSuggestionsStatusUpdateEvent implements Postable {

    @Getter
    private String activeStatus;

}
