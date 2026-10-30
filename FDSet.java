import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * This class represents a set of functional dependencies
 * 
 * @author David
 * @version 5/18/2022
 */
public class FDSet implements Iterable<FD> {
  private final Set<FD> fdset;

  /**
   * Constructs a functional dependency set
   * 
   * @param fds a variable list of FDs
   */
  public FDSet(FD... fds) {
    // we use a TreeSet to ensure FDs are sorted when printed
    this.fdset = new TreeSet<>();
    for (FD f : fds) {
      add(new FD(f));
    }
  }

  /**
   * Copy constructor creates a deep copy (clone) of the given FD Set
   * 
   * @param original an FD Set to copy
   */
  public FDSet(FDSet original) {
    this.fdset = new TreeSet<>();
    for (FD f : original.fdset) {
      add(new FD(f));
    }
  }

  /**
   * Adds a functional dependency to the set
   * 
   * @param fd Reference to a functional dependency
   */
  public void add(FD fd) {
    this.fdset.add(new FD(fd));
  }

  /**
   * Unions this FD set with another
   * 
   * @param other Reference to another FD set
   */
  public void addAll(FDSet other) {
    for (FD fd : other.fdset) {
      this.fdset.add(new FD(fd));
    }
  }

  /**
   * Deletes a functional dependency from the set
   * 
   * @param fd Reference to a functional dependency
   */
  public void remove(FD fd) {
    this.fdset.remove(fd);
  }

  /**
   * @return the Set of functional dependencies
   */
  public Set<FD> getSet() {
    Set<FD> copy = new TreeSet<>();

    for (FD fd : this.fdset) {
      copy.add(new FD(fd));
    }

    return copy;
  }

  /**
   * @return the number of FDs stored in this set
   */
  public int size() {
    return this.fdset.size();
  }

  @Override
  public int hashCode() {
    return this.fdset.hashCode();
  }

  /**
   * The default string representation is to have each FD appear on a separate
   * line.
   * 
   * @return a string representation of this FD set. One FD per line.
   */
  @Override
  public String toString() {
    return toString(true);
  }

  /**
   * Returns a string representation of this FD set. If line separated is desired,
   * each FD will be on its own line, tabbed over for prettier formatting:
   * [
   *  fd1
   *  fd2
   *  ...
   * ]
   * 
   * Otherwise, the format will be: [fd1, fd2, ...]
   * 
   * @param linesep whether the FDs should be separated one per line
   * @return
   */
  public String toString(boolean linesep) {
    String sep = linesep ? "\n\t" : ", ";
    StringJoiner joiner = new StringJoiner(sep);
    for (FD fd : fdset) {
      joiner.add(fd.toString());
    }
    if (linesep) {
      return "[\n\t" + joiner + "\n]";
    }
    return "[" + joiner + "]";
  }

  /**
   * Determines whether two FD sets contain the same FDs.
   * DO NOT confuse this method with logical equivalence of two FD sets.
   * 
   * @param other Reference to another FD set
   * @return true if this FDset and the given FDset contain the same set of FDs
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof FDSet)) {
      return false;
    }
    
    return this.fdset.equals(((FDSet)other).fdset);
  }

  /**
   * Two FD sets are logically equivalent iff their closures are equal.
   * 
   * @param other Reference to another FD set
   * @return true if logically equivalent to the given FDset
   */
  public boolean isEquivalentTo(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof FDSet)) {
      return false;
    }
    
    FDSet otherFDset = (FDSet) other;
    return FDUtil.fdSetClosure(this).getSet().equals(FDUtil.fdSetClosure(otherFDset).getSet());
  }

  /**
   * For a for-each loop to be used
   * 
   * @return an iterator for the FD Set elements
   */
  @Override
  public Iterator<FD> iterator() {
    return this.getSet().iterator();
  }
}
