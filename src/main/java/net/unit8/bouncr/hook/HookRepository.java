package net.unit8.bouncr.hook;

import enkan.collection.Multimap;
import net.unit8.bouncr.component.config.HookPoint;

import java.util.Optional;

import static enkan.util.ThreadingUtils.some;

public class HookRepository {
    private Multimap<HookPoint, Hook> hooks = Multimap.empty();

    public void register(HookPoint point, Hook hook) {
        synchronized (hooks) {
            hooks.put(point, hook);
        }
    }

    public void runHook(HookPoint point, Object message) {
        Optional.ofNullable(hooks.getAll(point))
                .ifPresent(hooks -> hooks.forEach((hook) -> hook.run(message)));
    }
}
