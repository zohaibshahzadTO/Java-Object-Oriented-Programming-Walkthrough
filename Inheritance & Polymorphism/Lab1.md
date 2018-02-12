# Lab 1: Interfaces keep things tidy

JUnit: AccessCountArrayListTest.java (FIXED AN ERROR JAN 24, 2018), CutoffIteratorTest.java, VowelComparatorTest.java

In this first lab, we take a look at class inheritance, decorators and strategy objects by creating our customized versions of a couple of important classes in the Java Collection framework.

# Class 1
First, create a custom collection subclass AccessCountArrayList<E> that extends ArrayList<E> and overrides the methods get and set so that they each maintain in a suitable private data field in the class a separate count of how many times these methods has been called, before calling the superclass version to return the actual result. Your methods don't need to perform any bounds checking, but can leave that to the superclass method that they invoke as part of their execution. This class should also define two accessor methods int getGetCount() and int getSetCount() to return these counts, and the mutator method void resetCounts() to reset both counts back to zero.

(The previous class might come handy when you want to count the exact number of array access operations performed by some array algorithm during its execution.)

# Class 2
Second, we move from collections to iterators. Create a decorator class CutoffIterator<E> that implements the interface Iterator<E> and has private fields Iterator<E> it (just like in every decorator ever, this field is a reference to the underlying object) and int limit, both initialized from the constructor arguments. Override the methods next and hasNext to call these methods of the underlying iterator object, except that once a total of limit elements have been iterated through using the method next, the hasNext method always returns false no matter how many elements still remain in the underlying collection.

(The previous class might come in handy in real programs, for turning infinite iterators into finite iterators of known fixed size that can safely be passed on to various polymorphic algorithms that receive iterators as parameters, without having to fear that algorithm falling into an infinite loop.)

# Class 3
Third, let us define a rather wacky (but perfectly legal by laws of both mathematics and man) ordering for String so that s1 is greater than s2 if s1 contains more vowels (aeiouy in either upper- or lowercase, but for simplicity, let’s ignore accents and other such special characters) than s2, regardless of the order in which these vowels are in and the other characters in these strings. For example, "boa" is greater than "scratch", since it has two vowels whereas the other word has only one.

Whenever two strings contain the same number of vowels, their comparison order is determined by the ordinary lexicographical order (that is, dictionary order already implemented as the compareTo method in String) comparison done for the reverses of these strings. For example, in this order "hello" is considered greater than "there", since in the ordinary lexicographic order, "olleh" is greater than "ereht". (The easiest way to reverse a string is to convert it to StringBuilder, reverse it there, and then convert back to String.)

Write a class VowelComparator that implements Comparator<String>, and override its method public int compare(String s1, String s2) to perform the above order comparison. Please note that even though the class VowelComparator extends a generic superclass, this class itself is not generic since it can only compare String objects and nothing else. You should therefore not define it as VowelComparator<String>, because the name String then becomes the placeholder name for type parameter instead of being the class java.lang.String as intended, resulting in a rather cryptic error message.

(Yeah, this class will never come handy in real programs for anybody. But the principles that you learned in writing it surely will.)
