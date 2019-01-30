import java.io.*;
import java.util.*;
public class wordSearchGenerator
{
   static Scanner input=new Scanner(System.in);
   static ArrayList<String> words=new ArrayList<String>();
   static char[][] puzzle;
   static int len, numWords, wLen, ranR, ranC, ranI;
   static String word;
   static boolean didSomething;
   public static void input(ArrayList<String> array, String filename) throws Exception
   {
      BufferedReader infile = new BufferedReader(new FileReader(filename));
      String s = infile.readLine();
      while(s != null)
      {
         s=s.toUpperCase();
         array.add(s);
         s = infile.readLine();
      }
      infile.close();
   }
   public static void showPuzzle()
   {
      for(int r=0; r<len; r++)
      {
         for(int c=0; c<len; c++)
            System.out.print(puzzle[r][c]);
         System.out.println();
      }
   }
   public static void setRandoms()
   {
      ranR=(int)(Math.random()*len);
      ranC=(int)(Math.random()*len);
      ranI=(int)(Math.random()*8);
   }
   public static void changeRandoms()
   {
      if(ranR+1<len)
         ranR++;
      else
      {
         ranR=0;
         if(ranC+1<len)
            ranC++;
         else
            ranC=0;
      }
   }
   public static boolean canPlaceVertically(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r+1<wLen)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c]!=' '&&puzzle[r-i][c]!=w.charAt(i))
               return false;
      }
      else
      {
         if(r+wLen>len)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c]!=' '&&puzzle[r+i][c]!=w.charAt(i))
               return false;
      }
      return true;
   }
   public static void placeVertically(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
         for(int i=0; i<wLen; i++)
            puzzle[r-i][c]=w.charAt(i);
      else
         for(int i=0; i<wLen; i++)
            puzzle[r+i][c]=w.charAt(i);
      didSomething=true;
   }
   public static boolean canPlaceHorizontally(String w, int r, int c, boolean left)
   {
      wLen=w.length();
      if(left)
      {
         if(c+1<wLen)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r][c-i]!=' '&&puzzle[r][c-i]!=w.charAt(i))
               return false;
      }
      else
      {
         if(c+wLen>len)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r][c+i]!=' '&&puzzle[r][c+i]!=w.charAt(i))
               return false;
      }
      return true;
   }
   public static void placeHorizontally(String w, int r, int c, boolean left)
   {
      wLen=w.length();
      if(left)
         for(int i=0; i<wLen; i++)
            puzzle[r][c-i]=w.charAt(i);
      else
         for(int i=0; i<wLen; i++)
            puzzle[r][c+i]=w.charAt(i);
      didSomething=true;
   }
   public static boolean canPlaceLeftDiagonally(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r+1<wLen||c+1<wLen)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c-i]!=' '&&puzzle[r-i][c-i]!=w.charAt(i))
               return false;
      }
      else
      {
         if(r+wLen>len||c+1<wLen)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c-i]!=' '&&puzzle[r+i][c-i]!=w.charAt(i))
               return false;
      }
      return true;
   }
   public static void placeLeftDiagonally(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
         for(int i=0; i<wLen; i++)
            puzzle[r-i][c-i]=w.charAt(i);
      else
         for(int i=0; i<wLen; i++)
            puzzle[r+i][c-i]=w.charAt(i);
      didSomething=true;
   }
   public static boolean canPlaceRightDiagonally(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r+1<wLen||c+wLen>len)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c+i]!=' '&&puzzle[r-i][c+i]!=w.charAt(i))
               return false;
      }
      else
      {
         if(r+wLen>len||c+wLen>len)
            return false;
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c+i]!=' '&&puzzle[r+i][c+i]!=w.charAt(i))
               return false;
      }
      return true;
   }
   public static void placeRightDiagonally(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
         for(int i=0; i<wLen; i++)
            puzzle[r-i][c+i]=w.charAt(i);
      else
         for(int i=0; i<wLen; i++)
            puzzle[r+i][c+i]=w.charAt(i);
      didSomething=true;
   }
   public static boolean canPlace(String w, int r, int c)
   {
      return canPlaceVertically(w, r, c, true)||canPlaceVertically(w, r, c, false)||canPlaceHorizontally(w, r, c, true)||canPlaceHorizontally(w, r, c, false)||canPlaceLeftDiagonally(w, r, c, true)||canPlaceLeftDiagonally(w, r, c, false)||canPlaceRightDiagonally(w, r, c, true)||canPlaceRightDiagonally(w, r, c, false);
   }
   public static void fillPuzzle()
   {
      for(int i=0; i<numWords; i++)
      {
         word=words.get(i);
         if(word.length()>wLen)
            wLen=word.length();
         len+=word.length();
      }
      len/=words.size();
      if(len<wLen)
         len=wLen;
      if(len<words.size())
         len=words.size();
      puzzle=new char[len][len];
      for(int r=0; r<len; r++)
         for(int c=0; c<len; c++)
            puzzle[r][c]=' ';
      for(int i=0; i<numWords; i++)
      {
         didSomething=false;
         word=words.get(i);
         setRandoms();
         while(!canPlace(word, ranR, ranC))
            changeRandoms();
         while(!didSomething)
         {
            if(ranI==0)
               if(canPlaceVertically(word, ranR, ranC, true))
                  placeVertically(word, ranR, ranC, true);
               else
                  ranI++;
            if(ranI==1)
               if(canPlaceVertically(word, ranR, ranC, false))
                  placeVertically(word, ranR, ranC, false);
               else
                  ranI++;
            if(ranI==2)
               if(canPlaceHorizontally(word, ranR, ranC, true))
                  placeHorizontally(word, ranR, ranC, true);
               else
                  ranI++;
            if(ranI==3)
               if(canPlaceHorizontally(word, ranR, ranC, false))
                  placeHorizontally(word, ranR, ranC, false);
               else
                  ranI++;
            if(ranI==4)
               if(canPlaceLeftDiagonally(word, ranR, ranC, true))
                  placeLeftDiagonally(word, ranR, ranC, true);
               else
                  ranI++;
            if(ranI==5)
               if(canPlaceLeftDiagonally(word, ranR, ranC, false))
                  placeLeftDiagonally(word, ranR, ranC, false);
               else
                  ranI++;
            if(ranI==6)
               if(canPlaceRightDiagonally(word, ranR, ranC, true))
                  placeRightDiagonally(word, ranR, ranC, true);
               else
                  ranI++;
            if(ranI==7)
               if(canPlaceRightDiagonally(word, ranR, ranC, false))
                  placeRightDiagonally(word, ranR, ranC, false);
               else
                  ranI=0;
         }
      }
      for(int r=0; r<len; r++)
         for(int c=0; c<len; c++)
            if(puzzle[r][c]==' ')
               puzzle[r][c]=(char)((int)(Math.random()*26)+65);
   }
   public static void main(String[] args) throws Exception
   {
      System.out.println("What's the filename of the list of words?");
      input(words, input.nextLine()+".txt");
      numWords=words.size();
      fillPuzzle();
      showPuzzle();
      System.out.println();
      System.out.print("Words: ");
      for(int i=0; i<numWords-1; i++)
         System.out.print(words.get(i)+", ");
      System.out.println(words.get(numWords-1));
   }
}