(ns three.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [three.core :refer :all]))

(def trees-map
  (three.core/process-trees-map
   (string/split (slurp "resources/test.txt") #"\n")))

(def example-one-answer 7)
(def example-one-plan [:x :x :x :y])

(deftest example-one
  (testing "Example for part one"
    (is (= example-one-answer
           (three.core/toboggan trees-map example-one-plan)))))

(def example-two-answer 336)
(def example-two-plans [[:x :y]
                        [:x :x :x :y]
                        [:x :x :x :x :x :y]
                        [:x :x :x :x :x :x :x :y]
                        [:x :y :y]])

(deftest example-two
  (testing "Example for part two"
    (is (= example-two-answer
           (apply * (mapv #(three.core/toboggan trees-map %)
                          example-two-plans))))))
