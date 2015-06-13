package com.rak.funnyfarmv4.gamestate;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Random;

import javax.print.attribute.standard.Finishings;

import sun.security.action.GetLongAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ScaleInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.BrownianAcceleration;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardRenderer;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.FloatCounter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.CollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btAxisSweep3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseProxy;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCapsuleShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithmConstructionInfo;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btConvexShape;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDispatcherInfo;
import com.badlogic.gdx.physics.bullet.collision.btGhostPairCallback;
import com.badlogic.gdx.physics.bullet.collision.btManifoldResult;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.collision.btSphereBoxCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btKinematicCharacterController;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.LinearMath;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw.DebugDrawModes;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
//import com.badlogic.gdx.tests.g3d.ParticleControllerTest.RotationAction;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.PerformanceCounter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.StringBuilder;

import com.rak.funnyfarmv4.Maingame;
import com.rak.funnyfarmv4.entities.WaterDroplet;
import com.rak.funnyfarmv4.entities.lala;
//import com.rak.funnyfarmv4.cls.GameObject;
//import com.rak.funnyfarmv4.cls.MyContactListener;
import com.rak.funnyfarmv4.extra.BulletConstructor;
import com.rak.funnyfarmv4.extra.BulletEntity;
import com.rak.funnyfarmv4.extra.BulletWorld;
import com.rak.funnyfarmv4.itf.Gps;
import com.rak.funnyfarmv4.manager.GameStateManager;
import com.rak.funnyfarmv4.manager.InputManager;

public class GameplayState extends BaseGameState implements InputProcessor,
		GestureListener {

	
	private final static String customDesktopLib = null;// "C:\\Xoppa\\code\\libgdx\\extensions\\gdx-bullet\\jni\\vs\\gdxBullet\\x64\\Debug\\gdxBullet.dll";
	private static boolean initialized = false;
	public FloatCounter fpsCounter = new FloatCounter(5);
	InputManager im;
	CameraInputController cameraInputController;

	public Environment environment;
	public DirectionalLight light;
	public ModelBatch shadowBatch;
	public static boolean shadows = true;
	public BulletWorld world;
	public ObjLoader objLoader = new ObjLoader();
	public ModelBuilder modelBuilder = new ModelBuilder();
	public ModelBatch modelBatch;
	public Array<Disposable> disposables = new Array<Disposable>();
	private int debugMode = DebugDrawModes.DBG_NoDebug;
	protected final static Vector3 tmpV1 = new Vector3(),
			tmpV2 = new Vector3();
	public PerspectiveCamera camera;

	public PerformanceCounter performanceCounter = new PerformanceCounter(this
			.getClass().getSimpleName());

	final int BOXCOUNT_X = 5;
	final int BOXCOUNT_Y = 5;
	final int BOXCOUNT_Z = 1;

	final float BOXOFFSET_X = -2.5f;
	final float BOXOFFSET_Y = 0.5f;
	final float BOXOFFSET_Z = 0f;

	BulletEntity wall;
	BulletEntity ground;
	BulletEntity character;

	BulletConstructor sceneConstructor1[] = new BulletConstructor[10];

	btGhostPairCallback ghostPairCallback;
	btPairCachingGhostObject ghostObject;
	btConvexShape ghostShape;
	btKinematicCharacterController characterController;
	Matrix4 characterTransform;
	Vector3 characterDirection = new Vector3();
	Vector3 walkDirection = new Vector3();

	public StringBuilder performance = new StringBuilder();
	Model[] modelGround = new Model[10];
	Model boxModel;

	private boolean LeftKey;
	private boolean RightKey;
	private boolean UpKey;
	private boolean DownKey;
	private boolean SpaceKey;

	
	
	
	public GameplayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}
	
	int loadCount;
	@Override
	public void RAKinit() {
		// TODO Auto-generated method stub
		initLoadingScreen();
		initBullet();
		initHUD();
		initEnvCam();
		
		initWeather();
		initParticle();
		initAsset();
		initLoad();
		
		

		
		
	}
	private void initHUD() {
		// TODO Auto-generated method stub
		ui = new Stage();
		stage = new Stage();
	}

//	public AssetManager assetsLoading;
	TextureRegion imgTexLoad;
	Image image;
	Skin skinload;
	Table loadingScreenTab;
	Stage loadingScreenStage;
	private void initLoadingScreen() {
		// TODO Auto-generated method stub
		skinload = new Skin(Gdx.files.internal("uiskin.json"));
		imgTexLoad = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
		
//		assetsLoading = new AssetManager();
//		assetsLoading.load(LOADING_SCREEN, Texture.class);
//		assetsLoading.load(DEFAULT_SKIN,Skin.class);
//		assetsLoading.finishLoading();
		
		loadingScreenTab = new Table();
		loadingScreenStage = new Stage();
		
		loadingScreenTab.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		loadingScreenStage.addActor(loadingScreenTab);
		loadingScreenTab.debug();
		
		image = new Image(imgTexLoad);
		image.setScaling(Scaling.fill);
		loadingScreenTab.add(image)
			.width(imgTexLoad.getRegionWidth())
			.height(imgTexLoad.getRegionHeight());
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		loadingScreenStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		loadingScreenStage.draw();
		
	}

	public static final String DEFAULT_PARTICLE = "data/flat_circle_particle_alpha.png",
			DEFAULT_SKIN = "uiskin.json",
			LOADING_SCREEN = "badlogic.jpg",
			TEXTURE_CHARACTER = "badlogic.jpg",
			MAP ="animation/g3db/terrain2.g3db",
			MODEL_CHARATER= "animation/g3db/char3.g3db"
			;
					
	
	private void initAsset() {
		// TODO Auto-generated method stub
		assets = new AssetManager();
		assets.load(DEFAULT_PARTICLE, Texture.class);
		assets.load(DEFAULT_SKIN, Skin.class);
		assets.load(TEXTURE_CHARACTER,Texture.class);
		assets.load(MODEL_CHARATER, Model.class);
		assets.load(MAP,Model.class);
		
	}

	private void initLoad() {
		// TODO Auto-generated method stub
		loading= new String[2];
		resultLoading = new boolean[2];
		
		for(int i=0;i<loading.length ;i++)
			loading[i]="n";
		
		for(int i=0;i<resultLoading.length ;i++)
			resultLoading[i]=false;
		
	}

	HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
	HttpURLConnection con = null ;
    InputStream is = null;
	
	private void initWeather() {
		// TODO Auto-generated method stub
		httpGet = new HttpRequest(HttpMethods.GET);
		httpGet.setUrl("http://api.openweathermap.org/data/2.5/weather?q=London,uk");
		
	}




	// Simulation
	Array<ParticleController> emitters;

	// Rendering
	// Environment environmentParticle;
	BillboardParticleBatch billboardParticleBatch;

	// UI
	Stage ui;
	
	StringBuilder builder,builderGpsla,builderGpslo,builderConnect;

	public AssetManager assets;
	

	private void initParticle() {
		
		
		emitters = new Array<ParticleController>();
		
	
		billboardParticleBatch = new BillboardParticleBatch();
		billboardParticleBatch.setCamera(camera);
		
		builder = new StringBuilder();
		builderGpsla = new StringBuilder();
		builderGpslo = new StringBuilder();
		builderConnect = new StringBuilder();
	}

	@Override
	public void RAKhandleInput() {
		// TODO Auto-generated method stub
		initController();
		// im = new InputManager();
		cameraInputController = new CameraInputController(camera);
		// im.setInputListener(im,inputController);
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
//		inputMultiplexer.addProcessor(cameraInputController);
//		inputMultiplexer.addProcessor(ui);
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	private void initController() {
		// TODO Auto-generated method stub
		LeftKey = false;
		RightKey = false;
		UpKey = false;
		DownKey = false;
		SpaceKey = false;
	}

	private void addController() {
		// TODO Auto-generated method stub
		characterTransform = character.transform; // Set by reference
		((btDiscreteDynamicsWorld) (world.collisionWorld))
				.addAction(characterController);
	}

	private void addToConstructorWorld() {
		// TODO Auto-generated method stub
		world.addConstructor("ground",
				new BulletConstructor(modelGround[0], 0f));
		world.addConstructor("wall1a", sceneConstructor1[0]);
		world.addConstructor("wall1b", sceneConstructor1[1]);
//		world.addConstructor("wall1c", new BulletConstructor(model, b));
//		world.addConstructor("wall1a", new BulletConstructor(modelGround[1], 0f));
//		world.addConstructor("wall1b", new BulletConstructor(modelGround[2], 0f));
		
		world.addConstructor("box", new BulletConstructor(boxModel, 1f)); // mass
																			// =
																			// 1kg:
																			// dynamic
																			// body
		world.addConstructor("staticbox", new BulletConstructor(boxModel, 0f)); // mass
																				// =
																				// 0:
																				// static
																				// body
		
		
		world.addConstructor("capsule", new BulletConstructor(capsule, null));
		
		
		
		
//		world.addConstructor("terrain", new BulletConstructor(model,0,b));
		
//		addConstructor for partivle into the world
//		world.addConstructor("particle",new);
		
		
	}
	Texture texture;
	Material material;
	
	btCollisionShape b;
	Model model,capsule,capsule2;
	btBvhTriangleMeshShape arenaShape;
//	ModelInstance characterAnim;
	AnimationController animation;
	int animStat=0;
	Array<ModelInstance> modInstances = new Array<ModelInstance>();
	
	final AnimationController.Transform trTmp = new AnimationController.Transform();
	final AnimationController.Transform trForward = new AnimationController.Transform();
	final AnimationController.Transform trBackward = new AnimationController.Transform();
	final AnimationController.Transform trRight = new AnimationController.Transform();
	final AnimationController.Transform trLeft = new AnimationController.Transform();
	
	private void buildModel() {
		// TODO Auto-generated method stub
		modelGround[0] = modelBuilder.createRect(20f, 0f, -20f, -20f, 0f, -20f,
				-20f, 0f, 20f, 20f, 0f, 20f, 0, 1, 0, new Material(
						ColorAttribute.createDiffuse(Color.WHITE),
						ColorAttribute.createSpecular(Color.WHITE),
						FloatAttribute.createShininess(16f)), Usage.Position
						| Usage.Normal);
		disposables.add(modelGround[0]);

		modelGround[1] = modelBuilder.createRect(10f, 10f, -10f, -10f, 10f,
				-10f, -10f, 0f, -10f, 10f, 0f, -10f, 1, 0, 0, new Material(
						ColorAttribute.createDiffuse(Color.WHITE),
						ColorAttribute.createSpecular(Color.WHITE),
						FloatAttribute.createShininess(16f)), Usage.Position
						| Usage.Normal);
		disposables.add(modelGround[1]);
//
		modelGround[2] = modelBuilder.createRect(10f, 0f, -10f, -10f, 0f, -10f,
				-10f, 10f, -10f, 10f, 10f, -10f, 1, 0, 0, new Material(
						ColorAttribute.createDiffuse(Color.WHITE),
						ColorAttribute.createSpecular(Color.WHITE),
						FloatAttribute.createShininess(16f)), Usage.Position
						| Usage.Normal);
		disposables.add(modelGround[2]);

		boxModel = modelBuilder.createBox(1f, 1f, 1f,
				new Material(ColorAttribute.createDiffuse(Color.WHITE),
						ColorAttribute.createSpecular(Color.WHITE),
						FloatAttribute.createShininess(64f)), Usage.Position
						| Usage.Normal);
		disposables.add(boxModel);

		sceneConstructor1[0] = new BulletConstructor(modelGround[1], 0f,
				new btBvhTriangleMeshShape(modelGround[1].meshParts));
		sceneConstructor1[1] = new BulletConstructor(modelGround[2], 0f,
				new btBvhTriangleMeshShape(modelGround[2].meshParts));

		
//		material = new Material(
//				TextureAttribute.createDiffuse(
//						(Texture)assets.get(TEXTURE_CHARACTER)
//						),
//				ColorAttribute.createSpecular(1, 1, 1, 1),
//				FloatAttribute.createShininess(8f));
//		
//		final long attributes = Usage.Position | Usage.Normal
//				| Usage.TextureCoordinates;
//		capsule = modelBuilder.createCapsule(2f, 6f, 32, material,
//				attributes);
		capsule = assets.get(MODEL_CHARATER);
		disposables.add(capsule);

		
		// Create the physics representation of the character
		ghostObject = new btPairCachingGhostObject();
		ghostShape = new btCapsuleShape(1f, 1f);

//		model = assets.get(MAP);
//		ModelInstance map = new ModelInstance(model);
//		sceneConstructor1[2] = new BulletConstructor(model, 0f,
//				Bullet.obtainStaticNodeShape(model.nodes));
//		model = assets.get(MAP);
//		Array<MeshPart> collisionarray = new Array<MeshPart>();
//		collisionarray.add(model.meshParts.get(0)); // The meshPart has no transformation
//		arenaShape = new btBvhTriangleMeshShape(collisionarray);
//		b= new btBvhTriangleMeshShape(collisionarray);
//		b = Bullet.obtainStaticNodeShape(model.getNode(""),true);
//		b = Bullet.obtainStaticNodeShape(model.nodes);
//		b.isNonMoving();
//		b.setLocalScaling(new Vector3(5f,5f,5f));
//		disposables.add(b);
		
		buildParticle(0);
		addToConstructorWorld();
		addToWorld();
		addController();

		
//		And add it to the physics world
		world.collisionWorld
				.addCollisionObject(
						ghostObject,
						(short) btBroadphaseProxy.CollisionFilterGroups.CharacterFilter,
						(short) (btBroadphaseProxy.CollisionFilterGroups.StaticFilter | btBroadphaseProxy.CollisionFilterGroups.DefaultFilter));

	}
	
	private void addToWorld() {
		// TODO Auto-generated method stub
	
		
		// Add the wall
		(world.add("wall1a", 0f, 1f, 0f)).setColor(
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(), 1f);
		(world.add("wall1b", 0f, 1f, 0f)).setColor(
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(), 1f);

//		 Add the ground
		(ground = world.add("ground", 0f, 0f, 0f)).setColor(
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(),
				0.25f + 0.5f * (float) Math.random(), 1f);
//		add the terrain
//		world.add("wall1c",0,0,0);
		
//		 Create some boxes to play with
		for (int x = 0; x < BOXCOUNT_X; x++) {
			for (int y = 0; y < BOXCOUNT_Y; y++) {
				for (int z = 0; z < BOXCOUNT_Z; z++) {
					world.add("box", BOXOFFSET_X + x, BOXOFFSET_Y + 20 + y,
							BOXOFFSET_Z + z).setColor(
							0.5f + 0.5f * (float) Math.random(),
							0.5f + 0.5f * (float) Math.random(),
							0.5f + 0.5f * (float) Math.random(), 1f);
				}
			}
		}
		
		character = world.add("capsule", 0f, 20f, 0f);
		character.transform.setTranslation(0, 5f, 0);
		setCharacter();
		
	}

	private void setCharacter() {
		// TODO Auto-generated method stub
		characterTransform=character.transform;
		ghostObject.setWorldTransform(character.transform);
		ghostObject.setCollisionShape(ghostShape);
		ghostObject
				.setCollisionFlags(btCollisionObject.CollisionFlags.CF_CHARACTER_OBJECT);
		characterController = new btKinematicCharacterController(ghostObject,
				ghostShape, .35f);
		
		character.transform.setToRotation(Vector3.Y, 90).trn(0, 1, 0);
		
		animation = new AnimationController(character.getModelInstance());
		animation.animate("Idle", -1, 1f, null, 0.2f);
		animStat = 1;
		for (Animation anim : character.getModelInstance().animations)
			Gdx.app.log("Test", anim.id);
	}

	public static Vector3 camPos=new Vector3(20, 20, 20); 
	
	@SuppressWarnings("deprecation")
	private void initEnvCam() {
		// TODO Auto-generated method stub
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f,
				0.3f, 0.3f, 1.f));
		light = shadows ? new DirectionalShadowLight(1024, 1024, 110f, 110f, 1f,
				300f) : new DirectionalLight();
		light.set(0.8f, 0.8f, 0.8f, -0.5f, -1f, 0.7f);
		environment.add(light);
		if (shadows)
			environment.shadowMap = (DirectionalShadowLight) light;

		shadowBatch = new ModelBatch(new DepthShaderProvider());
		modelBatch = new ModelBatch();

		world = createWorld();
		world.performanceCounter = performanceCounter;

		final float width = Gdx.graphics.getWidth();
		final float height = Gdx.graphics.getHeight();
		if (width > height)
			camera = new PerspectiveCamera(67f, 30f * width / height, 30f);
		else
			camera = new PerspectiveCamera(67f, 30f, 30f * height / width);
		camera.position.set(camPos);
		camera.near = 1f;
		camera.far = 1000f;
		camera.lookAt(0, 0, 0);
	}

	private BulletWorld createWorld() {
		// TODO Auto-generated method stub
		btDefaultCollisionConfiguration collisionConfiguration = new btDefaultCollisionConfiguration();
		btCollisionDispatcher dispatcher = new btCollisionDispatcher(
				collisionConfiguration);
		btAxisSweep3 sweep = new btAxisSweep3(new Vector3(-1000, -1000, -1000),
				new Vector3(1000, 1000, 1000));
		btSequentialImpulseConstraintSolver solver = new btSequentialImpulseConstraintSolver();
		btDiscreteDynamicsWorld collisionWorld = new btDiscreteDynamicsWorld(
				dispatcher, sweep, solver, collisionConfiguration);
		ghostPairCallback = new btGhostPairCallback();
		sweep.getOverlappingPairCache().setInternalGhostPairCallback(
				ghostPairCallback);
		return new BulletWorld(collisionConfiguration, dispatcher, sweep,
				solver, collisionWorld);
	}

	private void initBullet() {
		// TODO Auto-generated method stub
		if (initialized)
			return;
		// Need to initialize bullet before using it.
		if (Gdx.app.getType() == ApplicationType.Desktop
				&& customDesktopLib != null) {
			System.load(customDesktopLib);
		} else
			Bullet.init();
		Gdx.app.log("Bullet", "Version = " + LinearMath.btGetVersion());
		initialized = true;
	}

	String status="";
	String loading[];
	boolean resultLoading[];
	boolean finalResultLoading = false;
//	Texture particleTexture;
	static int indexParticle;
	
	Matrix4 tmpMatrix = new Matrix4(), tmpMatrix4 = new Matrix4();
	Vector3 tmpVector = new Vector3(); Vector3 tmpVector2 = new Vector3();
	Quaternion tmpQuaternion = new Quaternion();
	
//	int 
	
//	boolean Loaded[]=new boolean[2];
	int loadCur=0,loadMax=25;
	boolean loadResult=false;
	
	@Override
	public void RAKload() {
		// TODO Auto-generated method stub
		
		Gdx.app.log("RAKloaded GS", "di rak loaded");
		if(loading[0]=="n"&& assets.update() ){
				loading[0]="y";
				Gdx.app.log("RAKloaded GS", "hasil loadrak="+loading[0]);
				setupHUD();
				buildModel();		
		}
		if(loading[1]=="n"){
			Gdx.net.sendHttpRequest (httpGet, new HttpResponseListener() {
		        public void handleHttpResponse(HttpResponse httpResponse) {
	                status = httpResponse.getResultAsString();
	                //do stuff here based on response
	                loading[1]="y";
	                loadResult=true;
	                Gdx.app.log("status =",""+status);
	                
//	                Json json = new Json();
//	                MonsterFactory f = json.fromJson(MonsterFactory.class, 
//	                                              Gdx.files.internal("monsters.json"));
//		                JsonValue root = new JsonReader().parse(status);
		        }
		 
		        public void failed(Throwable t) {
	                status = "failed";
	                loading[1]="n";
	                loadCur++;
	                
	                if(loadCur==loadMax){
	    				loading[1]="y";
	    			}
	                
	                //do stuff here based on the failed attempt
	                Gdx.app.log("status ="," "+status);
		        }
				@Override
				public void cancelled() {
					// TODO Auto-generated method stub
					Gdx.app.log("status =","canceled"+status);
				}
			});
			
//			loading[1]="y"; // hpus kalo http get nya dipake
		}
		int j=0;
		for(int i=0;i<loading.length;i++){
			
			if(loading[i]=="y"){
//				gsm.loadRAK=false;
				resultLoading[i]=true;
				j++;
				Gdx.app.log("j =",""+j);
			}else{
				resultLoading[i]=false;
			}
			Gdx.app.log("loading ","ke ="+i+" hasilnya "+ resultLoading[i]);
			if (j==resultLoading.length) {
				gsm.loadRAK=false;
				Gdx.app.log("hasil gsm ","= "+ gsm.loadRAK);
			}
			
		}
		
		if(!gsm.loadRAK){
			loadingScreenStage.dispose();
			loadingScreenTab = null;
//			assetsLoading.clear();
//			assetsLoading.dispose();
			imgTexLoad=null;
			image=null;
			skinload.dispose();
			skinload=null;
			
		}else{
			Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			loadingScreenStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
			loadingScreenStage.draw();
		}
		
	}

//	private void addEmitter(float[] colors, Texture particleTexture,
//			Vector3 translation,Vector3 scale, Vector3 actionAxis, float actionRotation) {
//		ParticleController controller = createBillboardController(colors,
//				particleTexture);
//		controller.init();
//		controller.start();
//		emitters.add(controller);
//		controller.translate(translation);
//		controller.scale(scale);
//		
//		lala rain = new lala(controller,actionAxis,actionRotation);
//		
//		ui.addAction(rain);
//		
////		ui.addAction(new RotationAction(controller, actionAxis, actionRotation));
////		ui.addAction(new RotationAction(emitter, axis, angle))
//	}
	
	private WaterDroplet addWaterDroplet(float[] colors,Texture particleTexture,Vector3 translation){
		ParticleController controller = createBillboardController(colors,
				particleTexture);
		controller.init();
		controller.start();
		controller.translate(translation);
		controller.scale(0.1f,0.1f,0.1f);
		emitters.add(controller);
		
		
		WaterDroplet waterDroplet = new WaterDroplet(controller,Vector3.Zero,0,translation);
		
		return waterDroplet;
	}

	Label fpsLabel,gpsLabella,gpsLabello,connectLab;
	Touchpad touchpad;
	Stage stage;
	private void setupHUD() {
		Skin skin = assets.get(DEFAULT_SKIN);
		Table table = new Table();
		table.setFillParent(true);
		table.top().left().add(new Label("FPS =", skin)).left();
		table.add(fpsLabel = new Label("", skin)).left().expandX().row();
		table.top().left().add(new Label("GPS la =", skin)).left();
		table.add(gpsLabella = new Label("", skin)).left().row();
		table.top().left().add(new Label("GPS lo =", skin)).left();
		table.add(gpsLabello = new Label("", skin)).left().row();
		table.top().left().add(new Label("Connection =", skin)).left();
		table.add(connectLab = new Label("", skin)).left();
		ui.addActor(table);
		touchpad = new Touchpad(0, skin);
		touchpad.setBounds(200, 200, 200, 200);
		touchpad.setPosition(0, 0);
//		touchpad.setRotation(90);
//		touchpad.setCenterPosition(touchpad.get, touchpad.getCenterY());
		
		
		stage.addActor(touchpad);
	}
	int particleCalMax=45;
	int particleCalStart=0;
	WaterDroplet wtrDrop[]= new WaterDroplet[particleCalMax];
	Random random = new Random();
	
	
	public void buildParticle(int particleCal){
		billboardParticleBatch.setTexture((Texture) assets.get(DEFAULT_PARTICLE));
	
		for(int i=0;i<particleCal;i++){
			float ranValx = random.nextInt(21)-10;
			float ranValz = random.nextInt(21)-10;
			wtrDrop[i]= addWaterDroplet(new float[] { 0.12156863f, 0.047058824f, 1 },
					(Texture) assets.get(DEFAULT_PARTICLE),
					tmpVector.set(ranValx,camPos.y,ranValz)//posisi
					
					);
			particleCalStart++;
		}
				
	}
	
	public void addParticle(int particleCal){
		int particleCalStartCur=particleCalStart;
		for(int i=particleCalStartCur;i<particleCal+particleCalStartCur;i++){
			if(particleCalStart<particleCalMax){
				float ranValx = random.nextInt(21)-10;
				float ranValz = random.nextInt(21)-10;
				Gdx.app.log("adParticle", "i = "+i);
				wtrDrop[i]= addWaterDroplet(new float[] { 0.12156863f, 0.047058824f, 1 },
						(Texture) assets.get(DEFAULT_PARTICLE),
						tmpVector.set(ranValx,camPos.y,ranValz)//posisi
						);
				particleCalStart++;
//				ui.addAction(wtrDrop[i]);
//				ui.
			}
		}
	}
	private ParticleController createBillboardController(float[] colors,
			Texture particleTexture) {
		// Emission
		RegularEmitter emitter = new RegularEmitter();
		emitter.getDuration().setLow(100);//3k
		emitter.getEmission().setHigh(100);//3k
		emitter.getLife().setHigh(100);//3k
		emitter.setMaxParticleCount(100);//3k
		

		// Spawn
		PointSpawnShapeValue pointSpawnShapeValue = new PointSpawnShapeValue();
		pointSpawnShapeValue.xOffsetValue.setLow(0, 1f);// 0,1
		pointSpawnShapeValue.xOffsetValue.setActive(false);// true
		pointSpawnShapeValue.yOffsetValue.setLow(0, 1f);//
		pointSpawnShapeValue.yOffsetValue.setActive(false);//
		pointSpawnShapeValue.zOffsetValue.setLow(0, 1f);//
		pointSpawnShapeValue.zOffsetValue.setActive(false);// 
		SpawnInfluencer spawnSource = new SpawnInfluencer(pointSpawnShapeValue);

		// Scale
		ScaleInfluencer scaleInfluencer = new ScaleInfluencer();
		scaleInfluencer.value.setTimeline(new float[] { 0, 1 });// 0 1
		scaleInfluencer.value.setScaling(new float[] { 1, 0 });// 1 0
		scaleInfluencer.value.setLow(2);//0
		scaleInfluencer.value.setHigh(2);//1

		// Color
		ColorInfluencer.Single colorInfluencer = new ColorInfluencer.Single();
		colorInfluencer.colorValue.setColors(new float[] { colors[0],
				colors[1], colors[2], 0, 0, 0 });
		colorInfluencer.colorValue.setTimeline(new float[] { 0, 1 });
		colorInfluencer.alphaValue.setHigh(1);
		colorInfluencer.alphaValue
				.setTimeline(new float[] { 0, 0.5f, 0.8f, 1 });
		colorInfluencer.alphaValue
				.setScaling(new float[] { 0, 0.15f, 0.5f, 0 });

		// Dynamics
		DynamicsInfluencer dynamicsInfluencer = new DynamicsInfluencer();
		BrownianAcceleration modifier = new BrownianAcceleration();
		modifier.strengthValue.setTimeline(new float[] { 0, 1 });
		modifier.strengthValue.setScaling(new float[] { 0, 1 });
		modifier.strengthValue.setHigh(0);
		modifier.strengthValue.setLow(0);
		dynamicsInfluencer.velocities.add(modifier);
		
		return new ParticleController("Billboard Controller", emitter,
				new BillboardRenderer(billboardParticleBatch),
				new RegionInfluencer.Single(particleTexture), spawnSource,
				scaleInfluencer, colorInfluencer, 
				dynamicsInfluencer
				
				);
	}

	float timerCur=0;
	float timerMax=30;
	boolean addParBol=false;
	float timerMaxRain=0.0000000000001f;
	float timerCurRain=0;
	
//	float timer=0;
	float rainHigh=10;
	float rainMin=0;
	float rainCurMovDown[]= new float[particleCalMax];
	float rainCurMovSide[] = new float[particleCalMax];
	boolean just1time=false;
	float ranValxCur[] = new float[particleCalMax];//random.nextInt(((10) - (-10)) + 1) + (-10);
	float ranValzCur[] = new float[particleCalMax];//random.nextInt(((10) - (-10)) + 1) + (-10);
	
	
	public void Rain(float dt) {
		timerCurRain=timerCurRain+dt;
		
		int addarticle=2;
		for(int i=0;i<particleCalStart;i++){
//			Gdx.app.debug("jumlah particle =","= i");
//			Gdx.app.log("jumlah particle =","= "+i);
			if(rainCurMovDown[i]<-camPos.y){
				rainCurMovDown[i]=0;
				rainCurMovSide[i]=0;
				ranValxCur[i] = random.nextInt(40)-20;
				ranValzCur[i] = random.nextInt(40)-20;
			}
			rainCurMovDown[i]=rainCurMovDown[i]-0.6f;
			rainCurMovSide[i]=rainCurMovSide[i]+0.2f;
			wtrDrop[i].getEmitter().setTranslation(
					new Vector3(
					ranValxCur[i],
					camPos.y+rainCurMovDown[i],
					ranValzCur[i]+rainCurMovSide[i]));
		}
		if(particleCalStart<particleCalMax  && addParBol==true){
			addParticle(addarticle);
			addParBol=false;
		}
		if(timerCurRain>timerMaxRain+addarticle){
			timerCurRain=0;
			addParBol=true;
		}
	}
	
	@Override
	public void RAKupdate(float dt) {
		// TODO Auto-generated method stub
		CharacterControl();
		CharacterAnimate();
		camera.update();
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		
		modelBatch.begin(camera);
		world.render(modelBatch, environment);
		
//		shadowBatch.render(modInstances,environment);
		
		// world.render(particleSystem,null);

		Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
		if (debugMode != DebugDrawModes.DBG_NoDebug){
			world.setDebugMode(debugMode);
		}
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		
//		if(emitters.size > 0){
			//Update
			billboardParticleBatch.begin();
		
			for (ParticleController controller : emitters){
				controller.update();
				controller.draw();
			}
			billboardParticleBatch.end();
			modelBatch.render(billboardParticleBatch,environment);
			
//		}
		
		
		if (shadows) {
			((DirectionalShadowLight) light).begin(Vector3.Zero,
					camera.direction);
			shadowBatch.begin(((DirectionalShadowLight) light).getCamera());
			world.render(shadowBatch, null);
//			shadowBatch.render(modInstances);
			shadowBatch.end();
			((DirectionalShadowLight) light).end();
		}
		
		
		
		modelBatch.end();
		
		
		if(Maingame.gps.isEnable()==false && !just1time){
			Maingame.showConfirmDialog();
			just1time=true;
		}
//		if (Gdx.input.justTouched()) {
//			Gdx.app.debug("oi =","oi");
//			Gdx.input.vibrate(100);
//
//		}
		Rain(dt);
		reqCondition(dt);
		
		// Now we can update the world as normally

//		fpsCounter.put(Gdx.graphics.getFramesPerSecond());
//		performance.setLength(0);
//		performance.append("FPS: ").append(fpsCounter.value)
//				.append(", Bullet: ")
//				.append((int) (performanceCounter.load.value * 100f))
//				.append("%");
		
		
	}

	private void CharacterAnimate() {
		// TODO Auto-generated method stub
//		modelBatch.render(modInstances,environment);
		animation.update(Gdx.graphics.getDeltaTime());
	}

	private boolean reqConditionSucces =  false;
	
	private void reqCondition(float dt) {
		// TODO Auto-generated method stub
		timerCur=timerCur+dt;
//		Gdx.app.log("timer =",""+(timerCur));
		
		if(timerCur>timerMax && !reqConditionSucces){
			Gdx.net.sendHttpRequest (httpGet, new HttpResponseListener() {

				@Override
				public void handleHttpResponse(HttpResponse httpResponse) {
					// TODO Auto-generated method stub
					loadResult=true;
					Gdx.app.log("status =",""+status);
					reqConditionSucces=true;
				}

				@Override
				public void failed(Throwable t) {
					// TODO Auto-generated method stub
					 status = "failed";
					 loadResult=false;  		                
		             //do stuff here based on the failed attempt
		             Gdx.app.log("status ="," "+status);
				}

				@Override
				public void cancelled() {
					// TODO Auto-generated method stub
					loadResult=false;
					Gdx.app.log("status =","canceled"+status);
				}
			
			});
		} 
		if(timerCur>timerMax+5){
			timerCur=0;
			reqConditionSucces=false;
		}
	}

	boolean particleLoad = false;
	boolean particlestart = false;


	
	float angle=0;
	float angleCur=0;
	float angleFin=0;
	
//	float valueControl[] = new float[2];
	
	private void CharacterControl() {
		// TODO Auto-generated method stub

		if (LeftKey) {
			characterTransform.rotate(0, 1, 0, -1f);
			ghostObject.setWorldTransform(characterTransform);
		}
//		if (touchpad.getKnobPercentY()>0.4){
//			characterTransform.rotate(0, 1, 0, -1f);
//			ghostObject.setWorldTransform(characterTransform);
//		}
//		// Fetch which direction the character is facing now
//		characterDirection.set(-1, 0, 0).rot(characterTransform).nor();
//		// Set the walking direction accordingly (either forward or backward)
//		walkDirection.set(0, 0, 0);
//		if (touchpad.getKnobPercentY()>0.4)
//			walkDirection.add(characterDirection);
//		// if (Gdx.input.isKeyPressed(Keys.DOWN))
//		if (touchpad.getKnobPercentY()<-0.4)
//			walkDirection.add(-characterDirection.x, -characterDirection.y,
//					-characterDirection.z);
//		if(touchpad.getKnobPercentY()<0.2)
//		walkDirection.scl(4f * Gdx.graphics.getDeltaTime());
//		// And update the character controller
//		characterController.setWalkDirection(characterDirection);
//		world.update();
//		ghostObject.getWorldTransform(characterTransform);
		
		/*
		 * new Control
		 */
		
		characterDirection.set(-1, 0, 0).rot(characterTransform).nor();
		walkDirection.set(0, 0, 0);
		float phi=(float) Math.atan2(touchpad.getKnobPercentX(),touchpad.getKnobPercentY()); 
		
//		if(touchpad.getKnobPercentY()>0 || touchpad.getKnobPercentY()<0){
			
			
//			Gdx.app.log("", ""+phi);
			if(phi>=-0.2 && phi<=0.2 && (touchpad.getKnobPercentX()!=0 || touchpad.getKnobPercentY()!=0)){
				walkDirection.add(characterDirection);
			}else if(phi>0.2 && phi <= 1.2 ){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, -0.3f);
				
			}else if(phi>1.2 && phi <= 1.8){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, -0.9f);
			}else if(phi>1.8 && phi < 2.8){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, -1.6f);
			}
			if(phi<-0.2 && phi >= -1.2 ){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, 0.3f);
				
			}else if(phi<-1.2 && phi >= -1.8){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, 0.9f);
			}else if(phi<-1.8 && phi > -2.8){
				walkDirection.add(characterDirection);
				characterTransform.rotate(0, 1, 0, 1.6f);
			}
			if(phi<=-2.8 || phi >=2.8 && (touchpad.getKnobPercentX()!=0 || touchpad.getKnobPercentY()!=0)){
				walkDirection.add(-characterDirection.x, -characterDirection.y,
						-characterDirection.z);
				
			}
			walkDirection.scl(4f * Gdx.graphics.getDeltaTime());
			ghostObject.setWorldTransform(characterTransform);
//		}
//		if(touchpad.getKnobPercentY()<-0){
//			walkDirection.add(-characterDirection.x, -characterDirection.y,
//					-characterDirection.z);
//			walkDirection.scl(4f * Gdx.graphics.getDeltaTime());
//			Gdx.app.log("", ""+phi);
//		}
		
		characterController.setWalkDirection(walkDirection);
		world.update();
		ghostObject.getWorldTransform(characterTransform);
	}

	@Override
	public void RAKdraw(float dt) {
		// TODO Auto-generated method stub
//		float delta = Gdx.graphics.getDeltaTime();
		builder.delete(0, builder.length());
		builder.append(Gdx.graphics.getFramesPerSecond());
		fpsLabel.setText(builder);
		
		
		builderGpsla.delete(0,builderGpsla.length);
		builderGpsla.append(Maingame.la);
		gpsLabella.setText(builderGpsla);
		
		builderGpslo.delete(0,builderGpslo.length);
		builderGpslo.append(Maingame.lo);
		gpsLabello.setText(builderGpslo);

		builderConnect.delete(0,builderConnect.length);
		
		if(loadResult){
			builderConnect.append("Succes");
		}else{
			builderConnect.append("Failed");
		}
		connectLab.setText(builderConnect);
		
		ui.act(Gdx.graphics.getDeltaTime());
		ui.draw();
		stage.draw();
		
	}

	public void setDebugMode(final int mode) {
		world.setDebugMode(debugMode = mode);
	}

	public void toggleDebugMode() {
		if (world.getDebugMode() == DebugDrawModes.DBG_NoDebug)
			setDebugMode(DebugDrawModes.DBG_DrawWireframe
					| DebugDrawModes.DBG_DrawFeaturesText
					| DebugDrawModes.DBG_DrawText
					| DebugDrawModes.DBG_DrawContactPoints);
		else if (world.renderMeshes){
			world.renderMeshes = false;
			
		}
		else {
			world.renderMeshes = true;
			setDebugMode(DebugDrawModes.DBG_NoDebug);
		}
	}

	public void longPress() {
		toggleDebugMode();
	}

	@Override
	public void RAKdispose() {
		// TODO Auto-generated method stub
		world.dispose();
		world = null;

		for (Disposable disposable : disposables)
			disposable.dispose();
		disposables.clear();

		modelBatch.dispose();
		modelBatch = null;

		shadowBatch.dispose();
		shadowBatch = null;

		if (shadows)
			((DirectionalShadowLight) light).dispose();
		light = null;

		((btDiscreteDynamicsWorld) (world.collisionWorld))
				.removeAction(characterController);
		world.collisionWorld.removeCollisionObject(ghostObject);

		characterController.dispose();
		ghostObject.dispose();
		ghostShape.dispose();
		ghostPairCallback.dispose();
		
		
		assets.dispose();
		assets=null;
		for(int i=0;i<emitters.size;i++)
		emitters.get(i).dispose();
		ui.dispose();
		
		ground = null;
		wall = null;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		toggleDebugMode();
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.LEFT) {
			LeftKey = true;
		}
		if (keycode == Keys.RIGHT) {
			RightKey = true;
		}
		if (keycode == Keys.UP) {
			UpKey = true;
		}
		if (keycode == Keys.DOWN) {
			DownKey = true;
		}
		if (keycode == Keys.SPACE) {
			SpaceKey = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.ENTER) {
			toggleDebugMode();
			return true;
		}

		if (keycode == Keys.LEFT) {
			LeftKey = false;
		}
		if (keycode == Keys.RIGHT) {
			RightKey = false;
		}
		if (keycode == Keys.UP) {
			UpKey = false;
		}
		if (keycode == Keys.DOWN) {
			DownKey = false;
		}
		if (keycode == Keys.SPACE) {
			SpaceKey = false;
		}
		// return super.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

	


}