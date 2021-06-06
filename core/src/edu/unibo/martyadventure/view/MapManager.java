//This class load the maps from the assets
package edu.unibo.martyadventure.view;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

public class MapManager {

    private Vector2 playerStartPosition;
    private Vector2 biffStartPosition;


    // map names
    public static enum Maps {
        MAP1, MAP2, MAP3
    }


    // map path
    private static final String MAP_1_PATH = "Level/Map/map1.tmx";
    private static final String MAP_2_PATH = "Level/Map/map2.tmx";
    private static final String MAP_3_PATH = "Level/Map/map3.tmx";

    // layers name
    private static final String MARTY_SPAWN_LAYER_NAME = "MartySpawn";
    private static final String COLLISION_LAYER_NAME = "Collision";
    private static final String PACMAN_LAYER_NAME = "PacMan";
    private static final String ENEMY_SPAWN_LAYER_NAME = "EnemySpawn";
    private static final String BIFF_SPAWN_LAYER_NAME = "BiffSpawn";
    private static final String MARTY_SPAWN_OBJECT_NAME = "MartySpawnObject";
    private static final String BIFF_SPAWN_OBJECT_NAME = "BiffSpawnObject";

    // unit scale
    public final static float UNIT_SCALE = 1 / 16f;

    private Hashtable<Maps, String> mapTable;

    private TiledMap currentMap;
    private Future<TiledMap> preLoadedMap;
    private Maps currentMapName;
    private Maps preLoadedMapName;

    // map layers
    private MapLayer martySpawnLayer;
    private MapLayer biffSpawnLayer;
    private MapLayer collisionLayer;
    private MapLayer pacManLayer;
    private MapLayer enemySpawnLayer;


    /**
     * @return the map path for the given map name.
     */
    private String getMapPath(final Maps mapName) throws IOException {
        final String path = mapTable.get(mapName);
        if (path.isEmpty()) {
            throw new IOException("Invalid map path");
        }
        return path;
    }

    /**
     * @return the layer with the given name from the current map.
     */
    private MapLayer getLayer(final String layerName) throws IOException {
        final MapLayer layer = currentMap.getLayers().get(layerName);
        if (collisionLayer == null) {
            throw new IOException("No '" + layerName + "' layer found.");
        }
        return layer;
    }

    /**
     * @return the spawn point with the given name in the given layer.
     */
    private Vector2 getSpawnPoint(final MapLayer layer, final String objName) {
        final EllipseMapObject obj = (EllipseMapObject) layer.getObjects().get(objName);
        final Ellipse ellipse = obj.getEllipse();
        return new Vector2(ellipse.x * UNIT_SCALE, ellipse.y * UNIT_SCALE);
    }

    // initialize the hashtable
    public MapManager() {
        mapTable = new Hashtable<>();
        mapTable.put(Maps.MAP1, MAP_1_PATH);
        mapTable.put(Maps.MAP2, MAP_2_PATH);
        mapTable.put(Maps.MAP3, MAP_3_PATH);
    }

    /** @return the current loaded map **/
    public TiledMap getCurrentMap() {
        return currentMap;
    }

    /** @return the current loaded map name **/
    public Maps getCurrentMapName() {
        return currentMapName;
    }

    /**
     * Start loading the given map from file into memory
     * 
     * @param mapName the map name you want to start to load
     * @throws IOException
     **/
    public void preLoadMap(Maps mapName) throws IOException {
        final String mapPath = getMapPath(mapName);
        preLoadedMap = Toolbox.getMap(mapPath);
        preLoadedMapName = mapName;
    }

    /**
     * Load the given map name from file to the local memory
     *
     * @return the current loaded map name
     * @param mapName the map name you want to load
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     **/
    public void loadMap(Maps mapName) throws InterruptedException, ExecutionException, IOException {

        final String mapPath = getMapPath(mapName);

        // if we are using another map we dispose that and free memory
        if (currentMap != null) {
            currentMap.dispose();
        }
        /*
        //load the map with the toolbox and check
        if (preLoadedMap != null && preLoadedMapName.equals(mapName)) {
            currentMap = preLoadedMap.get();
            currentMapName = preLoadedMapName;
            preLoadedMap = null;
            preLoadedMapName = null;
        }
        else {
            currentMap = Toolbox.getMap(mapPath).get();
            currentMapName = mapName;
            preLoadedMap = null;
            preLoadedMapName = null;
        }*/
        currentMap = new TmxMapLoader().load(mapPath);

        if (currentMap == null) {
            throw new IOException("Map not loaded, loading error");
        }

        // getting layers
        this.collisionLayer = getLayer(COLLISION_LAYER_NAME);
        this.martySpawnLayer = getLayer(MARTY_SPAWN_LAYER_NAME);
        this.biffSpawnLayer = getLayer(BIFF_SPAWN_LAYER_NAME);
        this.enemySpawnLayer = getLayer(ENEMY_SPAWN_LAYER_NAME);
        this.pacManLayer = getLayer(PACMAN_LAYER_NAME);

        // Setting spawn points.
        this.playerStartPosition = getSpawnPoint(this.martySpawnLayer, MARTY_SPAWN_OBJECT_NAME);
        this.biffStartPosition = getSpawnPoint(this.biffSpawnLayer, BIFF_SPAWN_OBJECT_NAME);
    }

    /** @return the current loaded collision layer **/
    public MapLayer getCollisionLayer() {
        return collisionLayer;
    }

    /** @return the player start position of the current map **/
    public Vector2 getPlayerStartPosition() {
        return playerStartPosition;
    }

    /** @return biff start position of the current map **/
    public Vector2 getBiffStartPosition() {
        return biffStartPosition;
    }

    /** @return the current loaded marty layer **/
    public MapLayer getMartySpawnLayer() {
        return martySpawnLayer;
    }

    /** @return the current loaded pacman layer **/
    public MapLayer getPacManLayer() {
        return pacManLayer;
    }

    /** @return the current loaded enemy spawn layer **/
    public MapLayer getEnemySpawnLayer() {
        return enemySpawnLayer;
    }

    /** @return the current loaded biff layer **/
    public MapLayer getBiffSpawnLayer() {
        return biffSpawnLayer;
    }
}
