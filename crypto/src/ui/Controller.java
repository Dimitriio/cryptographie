package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener, KeyListener
{
 private Object model;
 private View view;
 protected boolean mousePressed;
 
 public Controller(Object newModel)
 {
  model = newModel;
 }
 
 /**
  * setView() is a setter which sets the View
  * @param view - the view to be set
  */
 public void setView(View view)
 {
  this.view = view;
 }
 
 /**
  * getView() is a getter which gets the view
  * @return the view
  */
 final public View getView()
 {
  return this.view;
 }
 
 /**
  * setModel() is a setter which sets the model
  * @param model - the model to be set
  */
 public void setModel(Object model)
 {
  this.model = model;
 }
 
 /**
  * getModel() is a getter which gets the model
  * @return the model
  */
 public Object getModel()
 {
  return this.model;
 }
 
 
 public void mousePressed(MouseEvent e)
 {
 }

 public void mouseReleased(MouseEvent e)
 {
 }

 public void mouseClicked(MouseEvent e)
 {
 }
 
 public void mouseEntered(MouseEvent e)
 {
 }

 public void mouseExited(MouseEvent e)
 {
 }
 
 public void mouseMoved(MouseEvent evt)
 {
 }
 
 public void mouseDragged(MouseEvent evt)
 {
 }
 
 public void keyTyped(KeyEvent evt)
 {
 }
 
 public void keyPressed(KeyEvent evt)
 {
 }

 public void keyReleased(KeyEvent evt)
 {
 }
}