package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Analize {
    private static HashMap<Integer, User> deleted;
    private static HashMap<Integer, User> added;
    private static HashMap<Integer, User> changed;

    public static Info diff(Set<User> previous, Set<User> current) {
        deleted = new HashMap<>();
        added = new HashMap<>();
        changed = new HashMap<>();
        addElements(current, previous);
        deleteElements(current, previous);
        return new Info(added.size(), changed.size(), deleted.size());
    }

    private static void addElements(Set<User> previous, Set<User> current) {
        Iterator<User> iteratorCurrent = current.iterator();
        Iterator<User> iteratorPrevious = previous.iterator();
        User previousEl = null;
        while (iteratorCurrent.hasNext()) {
            User currentEl = iteratorCurrent.next();
            if (iteratorPrevious.hasNext()) {
                previousEl = iteratorPrevious.next();
                added.put(previousEl.getId(), previousEl);
            }
            deleted.put(currentEl.getId(), currentEl);
            changed.put(currentEl.getId(), currentEl);
        }
        while (iteratorPrevious.hasNext()) {
            previousEl = iteratorPrevious.next();
            added.put(previousEl.getId(), previousEl);
        }
    }

    private static void deleteElements(Set<User> previous, Set<User> current)  {
        Iterator<User> iteratorCurrent = current.iterator();
        Iterator<User> iteratorPrevious = previous.iterator();
        User previousEl = null;
        while (iteratorCurrent.hasNext()) {
            User currentEl = iteratorCurrent.next();
            if (iteratorPrevious.hasNext()) {
                previousEl = iteratorPrevious.next();
                if (changed.get(previousEl.getId()) == null || Objects.equals(changed.get(previousEl.getId()), previousEl)) {
                    changed.remove(previousEl.getId());
                }
                deleted.remove(previousEl.getId());
            }
            if (added.remove(currentEl.getId()) == null) {
                changed.remove(currentEl.getId());
            }
        }
        while (iteratorPrevious.hasNext()) {
            previousEl = iteratorPrevious.next();
            if (changed.get(previousEl.getId()) == null || Objects.equals(changed.get(previousEl.getId()), previousEl)) {
                changed.remove(previousEl.getId());
            }
            deleted.remove(previousEl.getId(), previousEl);
        }
    }
}