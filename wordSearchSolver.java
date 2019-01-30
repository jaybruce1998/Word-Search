import java.io.*;
import java.util.*;
public class wordSearchSolver
{
   static Scanner input=new Scanner(System.in);
   static ArrayList<String> puz=new ArrayList<String>();
   static ArrayList<String> words=new ArrayList<String>();
   static char[][] puzzle;
   static int len, numWords, wLen;
   static String word, msg;
   static boolean foundWord;
   public static void input(ArrayList<String> array, String filename) throws Exception
   {
      BufferedReader infile = new BufferedReader(new FileReader(filename));
      String s = infile.readLine();
      while(!s.contains(":"))
      {
         s=s.toUpperCase();
         array.add(s);
         s = infile.readLine();
      }
      infile.close();
      s=s.toUpperCase();
      while(s.length()>0)
      {
         s=s.substring(s.indexOf(" ")+1);
         if(s.contains(","))
            words.add(s.substring(0, s.indexOf(",")));
         else
         {
            words.add(s);
            s="";
         }
      }
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
   public static String isVertical(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r-wLen<-1)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c]!=w.charAt(i))
               return "";
      }
      else
      {
         if(r+wLen>len)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c]!=w.charAt(i))
               return "";
      }
      if(up)
         return w+" is up from row "+(r+1)+" column "+(c+1)+".";
      return w+" is down from row "+(r+1)+" column "+(c+1)+".";
   }
   public static String isHorizontal(String w, int r, int c, boolean left)
   {
      wLen=w.length();
      if(left)
      {
         if(c+1<wLen)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r][c-i]!=w.charAt(i))
               return "";
      }
      else
      {
         if(c+wLen>len)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r][c+i]!=w.charAt(i))
               return "";
      }
      if(left)
         return w+" is left from row "+(r+1)+" column "+(c+1)+".";
      return w+" is right from row "+(r+1)+" column "+(c+1)+".";
   }
   public static String isLeftDiagonal(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r-wLen<-1||c+1<wLen)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c-i]!=w.charAt(i))
               return "";
      }
      else
      {
         if(r+wLen>len||c+1<wLen)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c-i]!=w.charAt(i))
               return "";
      }
      if(up)
         return w+" is up diagonally left from row "+(r+1)+" column "+(c+1)+".";
      return w+" is down diagonally left from row "+(r+1)+" column "+(c+1)+".";
   }
   public static String isRightDiagonal(String w, int r, int c, boolean up)
   {
      wLen=w.length();
      if(up)
      {
         if(r-wLen<-1||c+wLen>len)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r-i][c+i]!=w.charAt(i))
               return "";
      }
      else
      {
         if(r+wLen>len||c+wLen>len)
            return "";
         for(int i=0; i<wLen; i++)
            if(puzzle[r+i][c+i]!=w.charAt(i))
               return "";
      }
      if(up)
         return w+" is up diagonally right from row "+(r+1)+" column "+(c+1)+".";
      return w+" is down diagonally right from row "+(r+1)+" column "+(c+1)+".";
   }
   public static String is(String w, int r, int c)
   {
      return isVertical(w, r, c, true)+isVertical(w, r, c, false)+isHorizontal(w, r, c, true)+isHorizontal(w, r, c, false)+isLeftDiagonal(w, r, c, true)+isLeftDiagonal(w, r, c, false)+isRightDiagonal(w, r, c, true)+isRightDiagonal(w, r, c, false);
   }
   public static void findWords()
   {
      puzzle=new char[len][len];
      for(int r=0; r<len; r++)
         for(int c=0; c<len; c++)
            puzzle[r][c]=puz.get(r).charAt(c);
      showPuzzle();
      System.out.println();
      for(int i=0; i<numWords; i++)
      {
         word=words.get(i);
         foundWord=false;
         for(int r=0; !foundWord&&r<len; r++)
            for(int c=0; !foundWord&&c<len; c++)
            {
               msg=is(word, r, c);
               if(msg.length()>0)
               {
                  foundWord=true;
                  System.out.println(msg);
               }
            }
      }
   }
   public static void main(String[] args) throws Exception
   {
      System.out.println("What's the filename of the list of words?");
      input(puz, input.nextLine()+".txt");
      len=puz.size();
      numWords=words.size();
      findWords();
   }
}