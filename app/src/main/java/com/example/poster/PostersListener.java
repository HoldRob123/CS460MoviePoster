package com.example.poster;
/**
 * PostersListener interface defines a contract for poster action callbacks.
 */
public interface PostersListener {
    /**
     * Called when a poster is selected or deselected.
     *
     * @param isSelected True if a poster is selected; otherwise false.
     */
    void onPosterAction(boolean isSelected);
}
