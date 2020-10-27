package seedu.canoe.model.training;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.canoe.model.student.exceptions.DuplicateTrainingException;
import seedu.canoe.model.student.exceptions.TrainingNotFoundException;

/**
 * A list of trainings that enforces uniqueness between its elements and does not allow nulls.
 * A training is considered unique by comparing using {@code Training#isSameTraining(Training)}. As such, adding and
 * updating of trainings uses Training#isSameTraining(Training) for equality so as to
 * ensure that the training being added
 * or updated is unique in terms of identity in the UniqueTrainingList. However, the removal of a training uses
 * Training#equals(Object) so as to ensure that the training with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Training#isSameTraining(Training)
 */
public class UniqueTrainingList implements Iterable<Training> {

    private final ObservableList<Training> internalList = FXCollections.observableArrayList();
    private final ObservableList<Training> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent training as the given argument.
     */
    public boolean contains(Training toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTraining);
    }

    /**
     * Adds a training to the list.
     * The training must not already exist in the list.
     */
    public void add(Training toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTrainingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the training {@code target} in the list with {@code editedTraining}.
     * {@code target} must exist in the list.
     * The training identity of {@code editedTraining} must not be the same as another existing training in the list.
     */
    public void setTraining(Training target, Training editedTraining) {
        requireAllNonNull(target, editedTraining);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TrainingNotFoundException();
        }

        internalList.set(index, editedTraining);
    }

    /**
     * Removes the equivalent training from the list.
     * The training must exist in the list.
     */
    public void remove(Training toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TrainingNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code trainings}.
     * {@code trainings} must not contain duplicate trainings.
     */
    public void setTrainings(List<Training> trainings) {
        requireAllNonNull(trainings);
        if (!trainingsAreUnique(trainings)) {
            throw new DuplicateTrainingException();
        }

        internalList.setAll(trainings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Training> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Training> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTrainingList // instanceof handles nulls
                && internalList.equals(((UniqueTrainingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code trainings} contains only unique trainings.
     */
    private boolean trainingsAreUnique(List<Training> trainings) {
        for (int i = 0; i < trainings.size() - 1; i++) {
            for (int j = i + 1; j < trainings.size(); j++) {
                if (trainings.get(i).isSameTraining(trainings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
