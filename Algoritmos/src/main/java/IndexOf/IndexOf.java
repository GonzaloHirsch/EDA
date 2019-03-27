package IndexOf;

public class IndexOf {

    public static int IndexOf(char[] source, char[] target){
        int j;

        //Check if any of the strings is empty and throw an exception
        if (source.length == 0 || target.length == 0)
            throw new RuntimeException("Both arrays have to have at least one character");

        /*&& ( source.length - i) > target.length*/ //This can optimize if the target can be longer than the source
        for (int i = 0; i < source.length; i++){
            for (j = i; j < source.length &&  j - i < target.length && source[j] == target[j - i]; j++);
            if (j - i == target.length)
                return i;
        }
        return -1;
    }
}
