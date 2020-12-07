(ns three.core
  (:require [clojure.string :as string])
  (:gen-class))

(def input-file "resources/input.txt")

(defn process-trees-map
  [trees]
  (mapv (fn [line] (mapv (fn [char] (if (= "#" char)
                                      1
                                      0))
                         (string/split line #"")))
        trees))

(defn- coordinate-value
  [trees-map x y]
  (nth (nth trees-map y) x))

(defn toboggan
  [trees-map plan]
  (let [destination-y (dec (count trees-map))
        count-x       (dec (count (last trees-map)))
        steps         (count plan)]
    (loop [xpos  (if (= :x (nth plan 0))
                   1
                   0)
           ypos  (if (= :y (nth plan 0))
                   1
                   0)
           step  0
           trees 0]
      (if (= destination-y ypos)
        (+ trees
           (coordinate-value trees-map xpos ypos))
        (let [next-step (if (= step (dec steps))  ; if at end of plan, wrap around
                          0
                          (inc step))]
          (recur  (if (= :x (nth plan next-step)) ; if next step is move right, inc x
                    (if (= count-x xpos)          ; if at end of row, wrap around
                      0
                      (inc xpos))
                    xpos)
                  (if (= :y (nth plan next-step)) ; if next step is move down, inc y
                    (inc ypos)
                    ypos)
                  next-step
                  (if (= (count plan) (inc step)) ; count tree if last step of plan
                    (+ trees (coordinate-value trees-map xpos ypos))
                    trees)))))))

(defn -main
  [& args]
  (let [input     (string/split (slurp input-file) #"\n")
        trees-map (process-trees-map input)
        plans     [[:x :y]
                   [:x :x :x :y]
                   [:x :x :x :x :x :y]
                   [:x :x :x :x :x :x :x :y]
                   [:x :y :y]]
        part-one  (toboggan trees-map (nth plans 1))
        part-two  (apply * (mapv #(toboggan trees-map %) plans))]
    (println "Part one")
    (println "Answer:" part-one "\n")

    (println "Part two")
    (println "Answer:" part-two)))
