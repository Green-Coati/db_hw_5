import java.util.*;

public class Main {

  public static void main(String[] args) {
    // One FD Set = A --> B, AB --> C
    FD f1 = new FD(Set.of("A"), Set.of("A", "B")); // A --> B
    FD f2 = new FD(Set.of("A", "B"), Set.of("C")); // AB --> C
    FDSet fdset = new FDSet(f1, f2);

    // Another FD set: AB --> B, A --> BC, AB --> C
    FD g1 = new FD(Set.of("A", "B"), Set.of("B")); // AB --> B
    FD g2 = new FD(Set.of("A"), Set.of("C", "B")); // A --> BC
    FD g3 = new FD(Set.of("A", "B"), Set.of("C")); // AB --> C
    FDSet fdset2 = new FDSet(g1, g2, g3);

    // Output FD closures
    System.out.println(FDUtil.fdSetClosure(fdset));
    System.out.println(FDUtil.fdSetClosure(fdset2));

    // Test FD equality: fdset and gdset are logically equivalent iff their closures are equal
    System.out.println("Equals? " + (fdset.equals(fdset2)));
    System.out.println("Equivalent? " + (fdset.isEquivalentTo(fdset2)));
  }
}
