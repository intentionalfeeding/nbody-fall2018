public class Body {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
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
