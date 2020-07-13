package cz.muni.fi.pb162.project.geometry;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * @author Petr Micka
 * It is representation of LabeledPolygon in two dimensional space.
 */

public final class LabeledPolygon  extends SimplePolygon implements Polygon,Labelable,Sortable,PolygonWritable{
    private final Map<String,Vertex2D> vertices = new TreeMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     *
     * @param vertices vertices from what we will create labeled polygon.
     */
    private LabeledPolygon(Map<String ,Vertex2D> vertices){
        super(vertices.values().toArray(new Vertex2D[0]));
        this.vertices.putAll(vertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if(index < 0){
            throw new IllegalArgumentException();
        }
        return vertices.get(vertices.keySet().toArray(new String[0])[index%getNumVertices()]);
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * Get vertex stored under given label in a polygon.
     * If label does not exists, IllegalArgumentException is thrown.
     *
     * @param label label under which the vertex is stored
     * @return vertex with given label
     */
    @Override
    public Vertex2D getVertex(String label) {
        if(!vertices.containsKey(label)){
            throw new IllegalArgumentException();
        }
        return vertices.get(label);
    }

    /**
     * Method returns the labels of vertices in a polygon.
     * The labels are sorted in the ascending order lexicographically.
     *
     * @return collection of labels in ascending order
     */
    @Override
    public Collection<String> getLabels() {

        return Collections.unmodifiableSet(vertices.keySet());
    }

    /**
     * Finds all labels corresponding to given vertex.
     *
     * @param vertex vertex which labels are searched
     * @return collection of corresponding labels
     */
    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        return vertices.entrySet().stream().filter(e->(e.getValue().equals(vertex))).map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by he natural ordering of {@link Vertex2D} class.
     *
     * @return sorted vertices
     */
    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return new TreeSet<>(vertices.values());
    }

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by given comparator.
     *
     * @param comparator comparator object used to determine the ordering
     * @return sorted vertices
     */
    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        Set<Vertex2D> sorted = new TreeSet<>(comparator);
        sorted.addAll(vertices.values());
        return sorted;
    }

    /**
     *
     * @return all duplicate vertices in polygon.
     */
    public Collection<Vertex2D> duplicateVertices(){
        return vertices.entrySet().stream().filter(e->(Collections.frequency(vertices.values(),e.getValue())>1))
                .map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter writer = new BufferedWriter((new OutputStreamWriter(os)));
        try {
            for (Map.Entry<String,Vertex2D> entry : this.vertices.entrySet()) {
                Vertex2D vertex = entry.getValue();
                writer.write(vertex.getX() + " " + vertex.getY() + " " + entry.getKey());
            }
        } finally {
            writer.flush();
        }
    }

    @Override
    public void write(File file) throws IOException {
        try (OutputStream out = new FileOutputStream(file)){
            write(out);
        }
    }

    /**
     *
     * @param os output stream
     * @throws IOException if os is not working it will throw it
     */
    public void writeJson(OutputStream os) throws IOException {
        String jsonOutput = GSON.toJson(vertices);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        try {
            writer.write(jsonOutput);
        } finally {
            writer.flush();
        }

    }

    /**
     * @author Petr Micka
     * builder class for labeled polygon.
     */
    public static class Builder implements PolygonReadable{
        private Map<String,Vertex2D> map;

        /**
         * creates a new builder
         */
        public Builder(){
            map = new HashMap<>();
        }

        /**
         *
         * @param key under what we will save vertex
         * @param vertex to be saved
         * @return this polygon
         */
        public Builder addVertex(String key,Vertex2D vertex){
            if(key == null){
                throw new IllegalArgumentException("label can't be null!");
            }
            if(vertex == null){
                throw new IllegalArgumentException("vertex can't be null!");
            }
            map.put(key, vertex);
            return this;
        }

        /**
         *
         * @return builds polygon from all vertices saved.
         */
        public LabeledPolygon build() {
            return new LabeledPolygon(map);
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Map<String ,Vertex2D> map = new HashMap<>();
            String line = reader.readLine();
            while (line != null){
                addLineToPolygon(line ,map);
                line = reader.readLine();
            }
            this.map.putAll(map);
            return this;
        }

        /**
         *
         * @param line it will be splited and added to map
         * @param map map in witch it will be added
         * @throws IOException if line has bad format or X or Y is not a number
         */
        private void addLineToPolygon(String line ,Map<String,Vertex2D> map) throws IOException {
            String[] splitedLine= line.split(" ", 3);
            if(splitedLine.length < 3){
                throw new IOException("Line is't in correct format!");
            }
            try{
                map.put(splitedLine[2],new Vertex2D(Double
                        .parseDouble(splitedLine[0]),Double.parseDouble(splitedLine[1])));
            } catch (NumberFormatException e) {
                throw new IOException("X or Y is not a number!" ,e);
            }
        }

        @Override
        public Builder read(File file) throws IOException {
            try(InputStream in = new FileInputStream(file)){
                read(in);
            }
            return this;
        }
    }

}
