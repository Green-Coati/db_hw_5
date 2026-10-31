import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This utility class is not meant to be instantitated, and just provides some
 * useful methods on FD sets.
 * 
 * @author <<Your name>>
 * @version 10/21/2026
 */
public final class FDUtil {
  /**
   * Generates all trivial FDs over the given attribute set.
   * FDs with empty right-hand sides are omitted.
   *
   * @param attrs the relation's attributes; must not be modified
   * @return a new set containing all trivial FDs over the given attributes
   */
  public static FDSet trivial(final Set<String> attrs) {
    FDSet trivialFDs = new FDSet();
    List<Set<String>> powerSet = new ArrayList<>(powerSet(attrs));
    for (Set<String> set1 : powerSet) {
      for (Set<String> set2 : powerSet) {
        if (set1.containsAll(set2) && !set1.isEmpty() && !set2.isEmpty()) { // set2 subset set1 => set1 -> set2
          trivialFDs.add(new FD(set1, set2));
        }
      }
    }
    return trivialFDs;
  }

  /**
   * Augments every FD in the given set of FDs with the given attributes
   * 
   * @param fdset FD Set (Immutable)
   * @param attrs a set of attributes with which to augment FDs (Immutable)
   * @return a set of augmented FDs
   */
  public static FDSet augment(final FDSet fdset, final Set<String> attrs) {
    FDSet fdClosure = new FDSet(fdset);
    List<Set<String>> powerSet = new ArrayList<>(powerSet(attrs));

    for (Set<String> augmenter : powerSet) {
      if (augmenter.isEmpty()) continue;
      for (FD fd : fdset) {
        FD newFD = new FD(fd);
        newFD.addToLeft(augmenter);
        newFD.addToRight(augmenter);
        fdClosure.add(newFD);
      }
    }

    return fdClosure;
  }

  /**
   * Exhaustively resolves transitive FDs with respect to the given set of FDs
   * 
   * @param fdset (Immutable) FD Set
   * @return all transitive FDs with respect to the input FD set
   */
  public static FDSet transitive(final FDSet fdset) {
    FDSet fdClosure = new FDSet(fdset);
    for (FD fd1 : fdset) {
      for (FD fd2 : fdset) {
        if (fd1.getRight().equals(fd2.getLeft()))
          fdClosure.add(new FD(fd1.getLeft(), fd2.getRight()));
      }
    }

    return fdClosure;
  }

  /**
   * Generates the closure of the input FD set, omitting FDs with empty
   * right-hand sides. The relation's attributes are assumed to be exactly
   * those appearing on either side of the input FDs.
   *
   * @param fdset input FD set; must not be modified
   * @return a new FD set containing the closure
   */
  public static FDSet fdSetClosure(final FDSet fdset) {
    FDSet current = new FDSet(fdset); // make a copy of F

    Set<String> attributes = getAttributes(fdset);
    
    current.addAll(trivial(attributes));
    int prevSize;

    do {
      prevSize = current.size();
      current.addAll(augment(current, attributes));
      current.addAll(transitive(current));
    } while (current.size() != prevSize);

    return current;
  }

  // returns a set of all attributes present in an FDSet
  private static Set<String> getAttributes(FDSet fdset) {
    Set<String> attributes = new HashSet<>();
    for (FD fd : fdset) {
      for (String attrib : fd.getLeft()) {
        attributes.add(attrib);
      }
      for (String attrib : fd.getRight()) {
        attributes.add(attrib);
      }
    }
    return attributes;
  }

  /**
   * Generates all subsets of the input set, including the empty subset.
   *
   * @param set input set; must not be modified
   * @return the power set of the input set
   */
  public static <E> Set<Set<E>> powerSet(final Set<E> set) {
    // this cant be an optimal implementation
    // but this isnt algorithms
    // so i dont care
    E[] arr = (E[]) set.toArray();
    Set<Set<E>> powerSet = new HashSet<>();
    for (int i = 0; i < Math.pow(2, arr.length); i++) {
      String binaryString = Integer.toBinaryString(i);
      while (binaryString.length() < arr.length) {
        binaryString = "0" + binaryString;
      }
      String[] binary = binaryString.split("");
      Set<E> element = new HashSet<>();
      for (int j = 0; j < arr.length; j++) {
        int d = Integer.parseInt(binary[j]);
        if (d == 1) {
          element.add(arr[j]);
        }
      }
      powerSet.add(element);
    }

    return powerSet;
  }
}