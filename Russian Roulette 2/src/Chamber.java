
public class Chamber {
	private Integer ChamberSize;
	private Integer BulletLocale;
	private Integer HammerLocale;
	private Integer PlayerNumber;
	public boolean pullTheTrigger(){
		if(HammerLocale++ == BulletLocale){
			return true;
		}
		return false;
	}
	public Chamber(int PlayerNumbers){
		PlayerNumber = PlayerNumbers;
		ChamberSize = PlayerNumber * 3;
		ChamberSize /= 2;
		HammerLocale = 0;
		BulletLocale =(int) (Math.random() * ChamberSize);
	}
	public void reset(){
		PlayerNumber--;
		ChamberSize = PlayerNumber * 3 / 2;
		HammerLocale = 0;
		BulletLocale =(int) (Math.random() * ChamberSize);
	}
	public Integer numPlayers(){
		return PlayerNumber;
	}
	public Integer ChamberSize(){
		return ChamberSize;
	}
	public String toString(){
		return "Num People : " + PlayerNumber + ", Chamber Size : " + ChamberSize + ", HammerLocale : " + HammerLocale + ", BulletLocale : " + BulletLocale;
	}
	
}
