package com.app.arcoreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private ArFragment mArFragment;
    private ModelRenderable mFlowerRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面佈局檔的ArFragment
        mArFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        // 載入3D模型檔，其中的thenAccept()是傳入一個Lambda函式
        // 這個Lambda函式會把得到的3D模型物件存入我們宣告的mFlowerRenderable
        ModelRenderable.builder()
                .setSource(this, R.raw.flower)
                .build()
                .thenAccept(renderable -> mFlowerRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Log.e("ARCoreApp", "載入3D模型失敗");
                            return null;
                        });

        // ArFragment會分析Camera畫面，找出空間中的平面（Plane）
        // 我們可以設定這些平面的點選、拖曳...等觸控功能
        // 以下是把一個Lambda函式設定給平面的點選Listener
        // 這個Lambda函式的功能是在點選的地方，用前面建立的3D模型，做出一個虛擬物體
        mArFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    // 如果沒有3D模型物件就放棄執行
                    if (mFlowerRenderable == null) {
                        return;
                    }

                    // 在點選的地方做出一個虛擬物體
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(mArFragment.getArSceneView().getScene());
                    TransformableNode transformableNode = new TransformableNode(mArFragment.getTransformationSystem());
                    transformableNode.setParent(anchorNode);
                    transformableNode.setRenderable(mFlowerRenderable);
                    transformableNode.select();
                });
    }
}
