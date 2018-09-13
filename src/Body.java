public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	private double getX() {
		return myXPos;
	}
	private void setX(double myXPos) {
		this.myXPos = myXPos;
	}
	private double getY() {
		return myYPos;
	}
	private void setY(double myYPos) {
		this.myYPos = myYPos;
	}
	private double getXVel() {
		return myXVel;
	}
	private void setXVel(double myXVel) {
		this.myXVel = myXVel;
	}
	private double getYVel() {
		return myYVel;
	}
	private void setYVel(double myYVel) {
		this.myYVel = myYVel;
	}
	private double getMass() {
		return myMass;
	}
	private void setMass(double myMass) {
		this.myMass = myMass;
	}
	private String getName() {
		return myFileName;
	}
	private void setName(String myFileName) {
		this.myFileName = myFileName;
	}
	public Body(double xP, double yP, double xV, double yV, double m, String img){
		myXPos.setX(xP);
		myYPos.setY(yP);
		myXVel.setXVel(xV);
		myYVel.setYVel(yV);
		myMass.setMass(m);
		myFileName.setName(img);
	}
	public Body(Body p){
		myXPos = p.getX();
		myYPos = p.getY();
		myXVel = p.getXVel();
		myYVel = p.getYVel();
		myMass = p.getMass();
		myFileName = p.getName();
	}
	public double calcDistance(Body p){
		double distance;
		distance = Math.sqrt((this.getX()-p.getX())*(this.getX()-p.getX())+(this.getY()-p.getY())*(this.getY()-p.getY()));
		return distance;
	}
	public double calcForceExertedBy(Body p){
		double force;
		double G = 6.67E-11;
		force = G*p.getMass()*this.getMass()/(this.calcDistance(p)*this.calcDistance(p));	
		return force;
	}
	public double calcForceExertedByX(Body p){
		double force;
		force = this.calcForceExertedBy(p)*(p.getX()-this.getX())/this.calcDistance(p);
		return force;
	}
	public double calcForceExertedByY(Body p){
		double force;
		force = this.calcForceExertedBy(p)*(p.getY()-this.getY())/this.calcDistance(p);
		return force;
	}
	public double calcNetForceExertedByX(Body[] p){
		double force = 0;
		for (int k=0; k < p.length; k++){
		if (! p[k].equals(this)) {
		    force += this.calcForceExertedByX(p[k]);
		    }
		}
		return force;
	}
	public double calcNetForceExertedByY(Body[] p){
		double force = 0;
		for (int k=0; k < p.length; k++){
		if (! p[k].equals(this)) {
		    force += this.calcForceExertedByY(p[k]);
		    }
		}
		return force;
	}
	public void update(double seconds, double xforce, double yforce){
		this.setXVel(xforce/this.getMass()*seconds+this.getXVel());
		this.setYVel(yforce/this.getMass()*seconds+this.getYVel());
		this.setX(this.getXVel()*seconds+this.getX());
		this.setY(this.getYVel()*seconds+this.getY());
	}
	public void draw(){
		StdDraw.picture(this.getX(), this.getY(),"images/"+this.getName());
	}
}
