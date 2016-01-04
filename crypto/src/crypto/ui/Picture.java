package crypto.ui;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Picture {
	
	private File file;
	private List<Point> firsts = new ArrayList<Point>();
	private List<Point> lasts = new ArrayList<Point>();
	
	public Picture()
	{
		this.file = null;
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
	
	public List<Point> getFirsts()
	{
		return this.firsts;
	}
	
	public List<Point> getLasts()
	{
		return this.lasts;
	}
}
