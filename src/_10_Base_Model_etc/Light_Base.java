package _10_Base_Model_etc;

import javafx.scene.AmbientLight;
import javafx.scene.LightBase;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;

public class Light_Base
{
	//private         PointLight          light1 = new PointLight(Color.WHITE);
	//private         PointLight          light2 = new PointLight(Color.ANTIQUEWHITE);
	//private         PointLight          light3 = new PointLight(Color.ALICEBLUE);

//	// フォンシェーディングを設定
//    final PhongMaterial cylinderMaterial = new PhongMaterial();
//    // 拡散光による色の設定
//    cylinderMaterial.setDiffuseColor(Color.RED);
//    // スペキュラカラー（反射光の色）の設定
//    cylinderMaterial.setSpecularColor(Color.MAGENTA);
//    // マテリアルを設定
//    cylinder.setMaterial(cylinderMaterial);
//    // ドローモードを設定
//    cylinder.setDrawMode(DrawMode.FILL);


//	// フォンシェーディングを設定
//    final PhongMaterial boxMaterial = new PhongMaterial();
//    // 拡散光による色の設定
//    boxMaterial.setDiffuseColor(Color.GREEN);
//    // スペキュラカラー（反射光の色）の設定
//    boxMaterial.setSpecularColor(Color.LIGHTGREEN);
//    // マテリアルを設定
//    box.setMaterial(boxMaterial);
//    // ドローモードを設定
//    box.setDrawMode(DrawMode.FILL);
//
//    // フォンシェーディングを設定
//    final PhongMaterial sphereMaterial = new PhongMaterial();
//    // 拡散光による色の設定
//    sphereMaterial.setDiffuseColor(Color.BLUE);
//    // スペキュラカラー（反射光の色）の設定
//    sphereMaterial.setSpecularColor(Color.LIGHTCYAN);
//    // スペキュラパワーを設定
//    sphereMaterial.setSpecularPower(16.0d);
//    // マテリアルを設定
//    sphere.setMaterial(sphereMaterial);
//    // ドローモードを設定
//    sphere.setDrawMode(DrawMode.FILL);

//***************************************************************************
//    // アンビエントライト
//    AmbientLight ambient = new AmbientLight();
//    ambient.setColor(Color.rgb(184, 134, 11, 0.5));
//
//    // ポイントライト
//    PointLight point = new PointLight();
//    point.setColor(Color.GHOSTWHITE);
//
//    // ポイントライトを移動
//    point.setTranslateX(-800.0d);
//    point.setTranslateY(-300.0d);
//    point.setTranslateZ(-800.0d);
//
//    root.getChildren().addAll(box, cylinder, sphere, ambient, point);


//	private PointLight		m_light_point;
//	private AmbientLight	m_light_ambient;
	private LightBase	m_light;

	//
	public Light_Base(
		//ライトタイプ
	)
	{
		// アンビエントライト
//		AmbientLight ambient = new AmbientLight();
//		ambient.setColor(Color.rgb(184, 134, 11, 0.5));
		AmbientLight light_ambient = new AmbientLight();
		light_ambient.setColor(Color.rgb(184, 134, 11, 0.5));

		this.m_light = light_ambient;

//		// ポイントライト
//		m_light_point = new PointLight();
//		m_light_point.setColor(Color.GHOSTWHITE);
//
//		// ポイントライトを移動
//		m_light_point.setTranslateX(-800.0d);
//		m_light_point.setTranslateY(-300.0d);
//		m_light_point.setTranslateZ(-800.0d);
	}

	//************************************************************************//
	/**
	 *	getter
	*/
	//************************************************************************//
	public LightBase	getLight()	{return this.m_light;}
}
