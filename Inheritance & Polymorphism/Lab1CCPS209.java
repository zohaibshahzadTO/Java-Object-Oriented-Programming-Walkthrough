// Lab 1: Inheritance Keep Things Tidy
// Class 1

public class AccessCountArrayList<E> extends ArrayList<E> {

    private int getCount = 0, setCount = 0;

    public AccessCountArrayList(int capacity) {
      super(capacity);
    }

    public AccessCountArrayList() { }

    @Override
    public E get(int idx) {
    return this.getCount++;
    super.get(idx);
    }

    @Override
    public E set(int idx, E value) {
    // fill in body
    super.add(idx, value);
    }

    public int getGetCount() {

    // fill in body
    return this.getCount++;

    }

    public int getSetCount() {
    // fill in body
    return this.setCount++;

    }

    public void resetCounts() {
    // fill in body
    return 0;
    }
}

// Class 2

public class CutoffIterator<E> implements Iterator<E> {

    private int limit;
    private Iterator<E> it;

    public CutoffIterator (Iterator E, int limit) {
        this.limit = limit;
        this.it = it;
    }

    @Override public E next() {
      limit--;
      return next();
    }

    @Override public boolean hasNext() {
      if (limit > 0) {
        return true;
      }
      else {
        return false;
      }
    }
}

// Class 3

public class VowelComparator implements Comparator<String> {


  @Override public int compare (String s1, String s2) {
    int countS1 = countVowel(s1);
    int countS2 = countVowel(s2);
    int result = 0;
    String results1 = "";
    String results2 = "";

    if(CountS1 > CountS2) {
      result = 1;
    }
    else if(CountS1 < CountS2) {
      result = -1;
    }
    else if(CountS1 == CountS2) {
      for(int i = CountS1 - 1; i >= 0; i--) {
        results1 = Character.toString(s1.charAt(i));
      }

      for(int i = CountS2 - 1; i >= 0; i--) {
        results2 = Character.toString(s2.charAt(i));
      }
      result = result1.compareTo(results2);
    }
    return result;
  }

  public static int countVowels(String str) {
    int count = 0;
    for(int i = 0; i < str.length(); i++) {
      if(str.charAt(i) = 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
        count++;
      }
    }
    return count;
  }
}


// Class 3 (ver 2)

public class VowelComparator implements Comparator<String> {


  @Override public int compare (String s1, String s2) {
    int countS1 = countVowel(s1);
    int countS2 = countVowel(s2);
    int result = 0;
    String results1 = "";
    String results2 = "";

    if(CountS1 > CountS2) {
      result = 1;
    }
    else if(CountS1 < CountS2) {
      result = -1;
    }
    else if(CountS1 == CountS2) {
      s1 = ReverseStr(s1);
      s2 = ReverseStr(s2);

      if(s1.compareTo(s2) > 0) {return 1};
      if(s1.compareTo(s2) < 0) {return -1};
      if(s1.compareTo(s2) == 0) {return 0};
    }

  }

  public static int countVowels(String str) {
    int count = 0;
    for(int i = 0; i < str.length(); i++) {
      if(isVowel(str.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  private static String ReverseStr(String s) {
    String sr = new StringBuilder(s);
    sr = sr.reverse();
    s = sr.toString();
    return s;
  }

  public static boolean isVowel(char c) {
    return "AEIOUYaeiouy".indexOf(c) > -1;
  }






}
