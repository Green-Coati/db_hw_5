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
    // TODO
    return null;
  }

  /**
   * Augments every FD in the given set of FDs with the given attributes
   * 
   * @param fdset FD Set (Immutable)
   * @param attrs a set of attributes with which to augment FDs (Immutable)
   * @return a set of augmented FDs
   */
  public static FDSet augment(final FDSet fdset, final Set<String> attrs) {
    // TODO
    return null;
  }

  /**
   * Exhaustively resolves transitive FDs with respect to the given set of FDs
   * 
   * @param fdset (Immutable) FD Set
   * @return all transitive FDs with respect to the input FD set
   */
  public static FDSet transitive(final FDSet fdset) {
    // TODO
    return null;
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

    // TODO
    return current;
  }

  /**
   * Generates all subsets of the input set, including the empty subset.
   *
   * @param set input set; must not be modified
   * @return the power set of the input set
   */
  public static <E> Set<Set<E>> powerSet(final Set<E> set) {
    // TODO
    return null;
  }
}