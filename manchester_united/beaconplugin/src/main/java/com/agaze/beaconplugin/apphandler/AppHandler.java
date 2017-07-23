/**
 * Copyright (C) 2017
 *
 * @author  <a href="maria@agaze.in">maria
 *
 * @version $Revision: 1.0 $, on 10/7/17.
 *
 * @see  com.agaze.beaconplugin.apphandler.AppHandler
 *
 * @since ver 1.0
 *
 */

package com.agaze.beaconplugin.apphandler;

        import java.util.List;

        import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 *  {@link AppHandler} will interface the application
 */

public interface AppHandler {

    /**
     * Initialize the service  of detector layer
     *
     */
    public  void pluginInit();

    /**
     * start the notification
     *
     */
    public  void startNotification();

    /**
     * stop the notification
     */
    public  void stopNotification();

    /**
     * Returns whether the location {@code passedList}.
     *
     * @param locId
     *          the UUID of the location search for
     * @return {@code true} if this {@code List} has , {@code false}
     *         otherwise.
     *
     */
    public boolean isPassedLocation(String locId);

    /**
     * Returns whether the location {@code visitedList}.
     *
     * @param locId
     *          the UUID of the location search for
     * @param locTime
     *          the duration to calculate the visiting time
     * @return {@code true} if this {@code List} has , {@code false}
     *         otherwise.
     *
     */
    public boolean isVisitedLocation(String locId,int locTime);

    /**
     * Returns a {@code List} of the passed locations of this {@code List}
     *
     * @return  a list of a locations {@code List}.
     * @return Null if {@code List=0} is {@code null}.
     *
     */
    public List<String> passedLocations();

    /**
     * Returns a {@code List} of the visited locations of this {@code List}
     *
     * @param  locTime
     *          the duration to calculate the visiting time
     * @return  a list of a locations {@code List}.
     * @return Null if {@code List=0} is {@code null}.
     *
     */
    public List<String> visitedLocations(int locTime);

    /**
     * Returns the location ID {@code visitedList}.
     *
     * @param locTime
     *          the duration set to claculate the visiting time
     *
     * @return {@code true} if this {@code List} has , {@code false}
     *         otherwise.
     *
     */
    public String lastVisitedLocation(int locTime);

}