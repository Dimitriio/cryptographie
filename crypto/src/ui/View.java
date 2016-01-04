package ui;

import javax.swing.JPanel;

public abstract class View extends JPanel
{
 private Object model;
 private Controller controller;

 public View(Object model)
 {
  this.model = model;
  this.controller = defaultController(model);
  this.controller.setView(this);
  this.addMouseListener(this.controller);
  this.addMouseMotionListener(this.controller);
  this.addKeyListener(this.controller);
 }
 /**
  * setModel() is a setter which sets the model 
  * @param model - the model to be set
  */
 public void setModel(Object model)
 {
  this.model = model;
  this.controller.setModel(model);
 }
 
 /**
  * getModel() is a getter which gets the model
  * @return the model
  */
 public Object getModel()
 {
  return this.model;
 }
 
 /**
  * defaultController is a methode used to have a default controller
  * @param model is the model that we want to instanciate as default
  * @return the model instanciate
  */
 public Controller defaultController(Object model)
 {
  return new Controller(model);
 }
 
 /**
  * getController is a getter which gets the controller
  * @return the controller
  */
 final public Controller getController()
 {
  return this.controller;
 }
}