(ns three.core
  (:require [clojure.string :as string]
            [clojure.core.match :as match])
  (:gen-class))

(def input-file "resources/input.txt")

(defn process-trees-map
  [trees]
  (mapv (fn [line] (mapv (fn [char] (if (= "#" char) 1 0))
                         (string/split line #"")))
        trees))

(defn- coordinate-value
  [trees-map x y]
  (nth (nth trees-map y) x))

(defn- new-x
  [count-x xpos next-step]
  (match/match [(= :x next-step)
                (= count-x xpos)]
    [true  true]  0
    [true  false] (inc xpos)
    [false _]     xpos))

(defn- new-y
  [ypos next-step]
  (if (= :y next-step)   ; if next step is move down, inc y
    (inc ypos)
    ypos))

(defn toboggan
  [trees-map plan]
  (let [destination-y (dec (count trees-map))
        count-x       (dec (count (last trees-map)))
        steps         (count plan)
        first-step    (nth plan 0)]
    (loop [xpos  (if (= :x first-step) 1 0)
           ypos  (if (= :y first-step) 1 0)
           step  0
           trees 0]
      (if (= destination-y ypos)
        (+ trees (coordinate-value trees-map xpos ypos))
        (let [next-step-index (if (= step (dec steps))  ; if at end of plan, wrap around
                                0
                                (inc step))
              next-step       (nth plan next-step-index)]
          (recur  (new-x count-x xpos next-step)
                  (new-y ypos next-step)
                  next-step-index
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
