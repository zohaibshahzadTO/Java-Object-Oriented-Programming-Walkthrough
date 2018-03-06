# Sets

A set organizes its values in an order that is optimized for efficiency, which may not be the order in which you add elements. Inserting and removing elements is faster with a set than with a list.

# Choosing a Set Implementation

The set interface in the Java Library has the same methods as the collection interface. However, there is an essential difference between arbitrary collections and sets. A set does not admit duplicates. If you add an element to a set that is already present, the insertion is ignored.

The *HashSet* and *TreeSet* classes implement the Set interface. These two classes provide set implementations based on two different mechanisms, called hash tables, and binary search trees. Both implementations arrange the set elements so that finding, adding and removing elements is fast, but they use different strategies.

The basic idea of a *hash table*  is simple. Set elements are grouped into smaller collections
of elements that share the same characteristic. You can imagine a hash set of books as having a group for each color, so that books of the same color are in the same group. To find whether a book is already present, you just need to check it against the books in the same color group. Actually, hash tables don’t use colors, but integer values (called hash codes) that can be computed from the elements.

In order to use a hash table, the elements must have a method to compute those
integer values. This method is called hashCode. The elements must also belong to a class
with a properly defined equals method.

Many classes in the standard library implement these methods, for example String, Integer, Double, Point, Rectangle, Color, and all the collection classes. Therefore, you can form a HashSet<String>, HashSet<Rectangle>, or even a HashSet<HashSet<Integer>>.

Suppose you want to form a set of elements belonging to a class that you declared,
such as a HashSet<Book>. Then you need to provide hashCode and equals methods for the
class Book. There is one exception to this rule. If all elements are distinct (for example,
if your program never has two Book objects with the same author and title), then you
can simply inherit the hashCode and equals methods of the Object class.


The TreeSet class uses a different strategy for arranging its elements. Elements are kept in sorted order. For example, a set of books might be arranged by height, or alphabetically by
author and title. The elements are not stored in an array—that would make adding and removing elements too slow. Instead, they are stored in nodes, as in a linked list. However, the nodes are not arranged in a linear sequence but in a tree shape. In order to use a TreeSet, it must be possible to compare the elements and determine which one is “larger”. You can use a TreeSet for classes such as String and Integer that implement the Comparable interface, which we discussed in the Inheritance repository.

As a rule of thumb, you should choose a TreeSet if you want to visit the set’s elements
in sorted order. Otherwise choose a HashSet––as long as the hash function is well chosen, it is a bit more efficient.

When you construct a HashSet or TreeSet, store the reference in a Set<String> variable,
either as

  *Set<String> names = new HashSet<String>();*

  or

  *Set<String> names = new TreeSet<String>();*

After you construct the collection object, the implementation no longer matters; only the interface is important.

# Working with Sets

Adding and removing set elements are accompished with the add and remove methods:

  *names.add("Romeo");
   names.remove("Juliet");*

As in mathematics, a set collection in Java rejects duplicates. Adding an element has
no effect if the element is already in the set. Similarly, attempting to remove an element
that isn’t in the set is ignored.

The contains method tests whether an element is contained in the set:

  *if (names.contains("Juliet")) . . .*

Finally, to list all elements in the set, get an iterator. As with list iterators, you use the
next and hasNext methods to step through the set.

   *Iterator<String> iter = names.iterator();
    while (iter.hasNext())
    {
      String name = iter.next();
      Do something with name
    }*

We can also use the "for each" loop instead of explicitly using an iterator:

   *for (String name : names)
    {
      Do something with name
    }*

A set iterator visits the elements in the order in which the set implementation keeps
them. This is not necessarily the order in which you inserted them. The order of elements
in a hash set seems quite random because the hash code spreads the elements into different groups. When you visit elements of a tree set, they always appear in sorted order, even if you inserted them in a different order. There is an important difference between the Iterator that you obtain from a set and the ListIterator that a list yields. The ListIterator has an add method to add an element at the list iterator position. The Iterator interface has no such method. It makes no sense to add an element at a particular position in a set, because the set can order the elements any way it likes. Thus, you always add elements directly to a set, never
to an iterator of the set.

However, you can remove a set element at an iterator position, just as you do with list iterators. Also, the Iterator interface has no previous method to go backward through the
elements. Because the elements are not ordered, it is not meaningful to distinguish
between “going forward” and “going backward”.

# Maps

A map allows you to associate elements from a key set with elements from a value collection. You use a map when you want to look up objects by using a key. For example, you can show a map from names of people to their favorite colors.

Just as there are two kinds of implementations for sets, the Java library has two implementations for the Map Interface: HashMap and TreeMap.

After constructing a HashMap or TreeMap, you can store the reference to the map object in a Map reference:

  *Map<String, Color> favoriteColors = new HashMap<String, Color>();*

Use the put method to add an association:

  *favoriteColors.put("Juliet", Color.RED);*

You can change the value of an existing association, simply by calling put again:

  *favoriteColors.put("Juliet", Color.BLUE);*

The get method returns the value associated with a key:

  *Color julietsFavoriteColor = favoriteColors.get("Juliet");*

If you ask for a key that isn't associated with any values, then the get method returns null.

To remove an association, call the remove method with the key:

  *favoriteColors.remove("Juliet");*

Sometimes you want to enumerate all keys in a map. The keySet method yields the set of keys. You can then ask the key set for an iterator and get all keys. From each key, you can find the associated value with the get method. Thus, the following instructions prints all key/value pairs in a map m:

 *Set<String> keySet = m.keySet();
  for(String key : keySet)
  {
    Color value = m.get(key);
    System.out.println(key + "->" + value);
  }*

Check the sample program in MapDemo.java
