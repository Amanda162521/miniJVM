package org.mini.glfw;

import org.mini.gui.GCallback;

/**
 * Registered with Glfw3#glfwSetCallback(), reacts to all callback events of
 * Glfw
 *
 * @author mzechner
 *
 */
public interface GlfwCallback extends GCallback{

    /**
     * Called in case of an error, on the thread the error was generated on.
     *
     * @param error the error code
     * @param description the description of the error
     */
    public void error(int error, String description);

    /**
     * Called when a monitor was connected/disconnected
     *
     * @param monitor the monitor handle
     * @param connected whether the monitor was connected or disconnected
     */
    public void monitor(long monitor, boolean connected);

    /**
     * on framebuffer Size changed
     *
     * @param window
     * @param x
     * @param y
     */
    public void framebufferSize(long window, int x, int y);

    /**
     * Called when the position of a window changed
     *
     * @param window the window handle
     * @param x the new x coordinate, in pixels, of the top left corner of the
     * client area of the window
     * @param y the new y coordinate, in pixels, of the top left corner of the
     * client area of the window
     */
    public void windowPos(long window, int x, int y);

    /**
     * Called when the size of the window changed
     *
     * @param window the window handle
     * @param width the new width in pixels
     * @param height the new height in pixels
     */
    public void windowSize(long window, int width, int height);

    /**
     * Called when the window should be closed.
     *
     * @param window the window handle
     * @return whether to allow closing the window
     */
    public boolean windowClose(long window);

    /**
     * Called when the window content needs to be refreshed
     *
     * @param window the window handle
     */
    public void windowRefresh(long window);

    /**
     * Called when the focus of the window changed
     *
     * @param window the window handle
     * @param focused whether the window is focused
     */
    public void windowFocus(long window, boolean focused);

    /**
     * Called when the window is (de-)iconified
     *
     * @param window the window handle
     * @param iconified whether the window is iconified
     */
    public void windowIconify(long window, boolean iconified);

    /**
     * Called when a key was pressed or released
     *
     * @param window the window handle
     * @param key the key code
     * @param scancode the scan code as reported by the OS
     * @param action whether the key was pressed (GLFW_PRESSED), released
     * (GLFW_RELEASE) or repeated (GLFW_REPEAT)
     * @param mods the modifier flags
     */
    public void key(long window, int key, int scancode, int action, int mods);

    /**
     * Called when a Unicode character was generated by the keyboard
     *
     * @param window the window handle
     * @param character the Unicode character
     */
    public void character(long window, char character);

    /**
     * Called when a mouse button was pressed or released
     *
     * @param window the window handle
     * @param button the button id
     * @param pressed whether the button was pressed (true) or released (false)
     */
    public void mouseButton(long window, int button, boolean pressed);

    /**
     * Called when the mouse cursor moved
     *
     * @param window the window
     * @param x the x-coordinate in client area coordinates
     * @param y the y-coordinate in client area coordinates
     */
    public void cursorPos(long window, int x, int y);

    /**
     * Called when the mouse entered the client area
     *
     * @param window the window handle
     * @param entered whether the mouse cursor entered or left the window
     */
    public void cursorEnter(long window, boolean entered);

    /**
     * Called when the mouse wheel was scrolled
     *
     * @param window the window handle
     * @params scrollX The scroll offset along the x-axis.
     * @params scrollY The scroll offset along the y-axis.
     */
    public void scroll(long window, double scrollX, double scrollY);

    /**
     * if there are some files drag into the window
     * @param window
     * @param count
     * @param paths 
     */
    public void drop(long window, int count, String[] paths);
    
    
    public void mainLoop();

}
