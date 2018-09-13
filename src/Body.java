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
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	public Body(Body p){
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	public double calcDistance(Body p){
		double distance;
		distance = Math.sqrt((this.myXPos-p.myXPos)*(this.myXPos-p.myXPos)+(this.myYPos-p.myYPos)*(this.myYPos-p.myYPos));
		return distance;
	}
	public double calcForceExertedBy(Body p){
		double force;
		double G = 6.67E-11;
		force = G*p.myMass*this.myMass/(this.calcDistance(p)*this.calcDistance(p));	
		return force;
	}
	public double calcForceExertedByX(Body p){
		double force;
		force = this.calcForceExertedBy(p)*(p.myXPos-this.myXPos)/this.calcDistance(p);
		return force;
	}
	public double calcForceExertedByY(Body p){
		double force;
		force = this.calcForceExertedBy(p)*(p.myYPos-this.myYPos)/this.calcDistance(p);
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
		this.myXVel = xforce/this.myMass*seconds+this.myXVel;
		this.myYVel = yforce/this.myMass*seconds+this.myYVel;
		this.myXPos = this.myXVel*seconds+this.myXPos;
		this.myYPos = this.myYVel*seconds+this.myYPos;
	}
	public void draw(){
		StdDraw.picture(myXPos, myYPos,"images/"+myFileName);
	}
}
