(ns one.core
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

(defn- fuel-calculation
  "Calculate fuel required for given mass."
  [mass]
  (-> mass
      (/ 3)
      (math/floor)
      (- 2)))

(defn all-modules
  "Calculate fuel required for mass of all modules."
  [input]
  (apply +
         (map
          fuel-calculation
          input)))

(defn all-modules-plus-extra-fuel
  "Calculate fuel required for mass of all modules plus mass of the extra fuel."
  [input]
  (apply +
         (map
          (fn [mass]
            (loop [extra-fuel (fuel-calculation mass)
                   total-fuel 0]
              (if (<= extra-fuel 0)
                total-fuel
                (recur (fuel-calculation extra-fuel)
                       (+ total-fuel extra-fuel)))))
          input)))

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split
                                 (slurp "resources/input.txt")
                                 #"\n"))]
    (println "Part one")
    (println "Answer:" (str (all-modules input)))

    (println "Part two")
    (println "Answer:" (str (all-modules-plus-extra-fuel input)))))
