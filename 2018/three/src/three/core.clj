(ns three.core
  (:gen-class)
  (:require [clojure.core.matrix :as mat]))

(defn- process-claim
  "Return [n x y width height] from claim definition '#n @ x,y: wxh'."
  [claim]
  (map read-string
       (rest (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$" claim))))

(defn- increment-idx!
  "Increment the number at coordinates x:y in a mutable 2D matrix."
  [m x y]
  (mat/mset! m x y (inc (mat/mget m x y))))

(defn- apply-claims
  "Apply patches to 1000x1000 2D matrix and return the immutable, patched matrix."
  [input]
  (let [fabric (mat/zero-matrix :vectorz 1000 1000)]
    (doseq [claim (map process-claim input)]
      (doseq [x (range (nth claim  1)
                       (+ (nth claim 1) (nth claim 3)))
              y (range (nth claim 2)
                       (+ (nth claim 2) (nth claim 4)))]
        (increment-idx! fabric x y)))
    (mat/immutable fabric)))

(defn multiple-patched-inches
  "Total square inches that have been been patched by more than one claim."
  [input]
  (let [patched-fabric      (apply-claims input)
        square-inches       (mat/ecount patched-fabric)
        unpatched-inches    (mat/zero-count patched-fabric)
        patched-once        (mat/eq patched-fabric 1)
        patched-once-inches (- (mat/ecount patched-once)
                               (mat/zero-count patched-once))]
    (- square-inches
       unpatched-inches
       patched-once-inches)))

(defn unique-claim
  "ID of claim that has no overlap."
  [input]
  (let [patched-fabric (apply-claims input)
        ids (for [claim (map process-claim input)]
              [(nth claim 0)
               (apply *
                      (for [x (range (nth claim  1)
                                     (+ (nth claim 1) (nth claim 3)))
                            y (range (nth claim 2)
                                     (+ (nth claim 2) (nth claim 4)))]
                        (mat/mget patched-fabric x y)))])]
    (first (first (filter #(== (nth % 1) 1) ids)))))

(defn -main
  [& args]
  (let [input (clojure.string/split (slurp "resources/input.txt")
                                    #"\n")]
    (println "Part one")
    (println "Answer:" (multiple-patched-inches input))

    (println "Part two")
    (println "Answer:" (unique-claim input))))
