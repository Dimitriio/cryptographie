package crypto.ui;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Picture {
	
	private File file;
	private ArrayList<Point> firsts = new ArrayList<Point>();
	private ArrayList<Point> lasts = new ArrayList<Point>();
	private Coded coded;
	
	public Picture()
	{
		this.file = null;
		this.coded = null;
	}
	
	public Picture(File file) {
		this.file = file;
	}

	public File getFile()
	{
		return this.file;
	}
	
	public void addFirst(Point p)
	{
		this.firsts.add(p);
	}
	
	public void addLast(Point p)
	{
		this.lasts.add(p);
	}
	
	public ArrayList<Point> getFirsts()
	{
		return this.firsts;
	}
	
	public ArrayList<Point> getLasts()
	{
		return this.lasts;
	}
	
	public void setCoded(Coded coded)
	{
		this.coded = coded;
	}

	public Coded getCoded() {
		return this.coded;
	}
}
