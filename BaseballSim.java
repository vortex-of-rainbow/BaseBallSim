/** 打率と得点期待値の非常に簡易なシミュレーション. */
public class BaseballSim {
	
	boolean	firstRunner = false;		//	一塁にランナーがいればtrue、いなければfalse。
	boolean	secondRunner = false;		//	二塁にランナーがいればtrue、いなければfalse。
	int inning = 1;		      		//	イニング
	int outCount = 0;			//	アウトカウント
	int score = 0;				//	得点数
	int totalScore = 0;			//	総得点数
	int numTrial = 0;			//	これまで試行された回数
	int outputCount = 1;			//	これまで試行された回数が、この値に等しくなったら途中経過を表示
	int limit = 1000000;			//	試行回数の上限
	double average = 0.3;			 //  打率	
	
	/** メインルーチン. */
	public static void main( String[] args ){
	
		BaseballSim bs = new BaseballSim();
	
	}
	
	/** コンストラクタ. */
	public BaseballSim(){
	
		do{
			
			execGame();			//	試合を試行
			numTrial++;			//	試行された回数に１を加算
			
			if( numTrial == outputCount ){
				
				System.out.print( numTrial );
				System.out.print( "試合終了時点での平均得点　：　");
				System.out.print( ( (double) totalScore ) / numTrial );
				System.out.println( "点" );
				//	途中経過を表示
				
				outputCount *= 10;		//	次に途中経過を表示する回数を、現在の十倍に指定
				
			}
		
		}while( numTrial < limit );
		//	試行が終わるまで回す
		
	}
	
	/** １試合の処理. */
	private void execGame(){
	
		do{
		
			execInning();	//	イニングを実行
			inning++;		//	イニングの回数に１を加算
		
		} while( inning <= 9 );
		//	９イニングが終わるまで回す
	
		totalScore += score;		//	総得点に得点を加算
		
		score = 0;
		inning = 1;
		//	スコア・イニングをクリアして戻る
	
	}
	
	/** １イニングの処理. */
	private void execInning(){
	
		do{
			
			if( Math.random() <= average ){
			
				hit();				//	乱数が打率以上ならばヒットの処理
			
			} else {
				
				outCount++;			//	打率以下ならばアウトカウントに１を加算
			
			}
			
		} while ( outCount < 3 );
		//	アウトが３になるまで回す
		
		outCount = 0;
		firstRunner = false;
		secondRunner = false;
		//　アウトカウント、残塁をクリアして戻る
	
	}
	
	/** ヒットが出た時の処理. */
	private void hit(){
	
		if( !firstRunner ){
	
			firstRunner = true;			//	一塁にランナーがいなければ、一塁にランナーを置く
	
		} else {
		
			if ( secondRunner ){
			
				score++;				//	二塁にランナーがいれば得点に１を加算
			
			} else {
			
				secondRunner = true;	//	一塁にのみランナーがいる場合には、二塁にもランナーを置く
				
			}
		
		}
	
	}
	
}
