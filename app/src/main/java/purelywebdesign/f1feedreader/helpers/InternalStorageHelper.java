package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import purelywebdesign.f1feedreader.entities.Race;

/**
 * Created by Anthony on 22/03/2015.
 * Based on example at https://androidresearch.wordpress.com/2013/04/07/caching-objects-in-android-internal-storage/
 */
public class InternalStorageHelper {

    private InternalStorageHelper(){}

    /**
     * Caches the given ArrayList of race objects to the apps localStorage
     * @param context Fragment that the call relates to
     * @param key The key value of the local storage item
     * @param races The ArrayList of races
     */
    public static void writeObject(Context context, String key, ArrayList<Race> races){
        try {
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(races);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves cached objects from local storage
     * @param context: The fragment the call originally came from
     * @param key: The key of the local storage item.
     * @return Hopefully, a list of objects
     */
    public static Object readObjects(Context context, String key){

        try {
            FileInputStream fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
