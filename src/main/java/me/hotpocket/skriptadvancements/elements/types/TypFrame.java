package me.hotpocket.skriptadvancements.elements.types;

import ch.njol.skript.registrations.Classes;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.EnumClassInfo;

public class TypFrame {
    static{
        if(Classes.getExactClassInfo(AdvancementFrameType.class) == null){
            Classes.registerClass(new EnumClassInfo<>(AdvancementFrameType.class, "frame")
                    .user("frames")
                    .name("Advancement Frame")
                    .description("Represents an advancement frame. (Challenge, Goal, Task)")
                    .since("1.3")
            );
        }
    }
}
