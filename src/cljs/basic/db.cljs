(ns basic.db
  (:require [reagent.core :as r])
  (:require-macros [basic.macros :refer [readfile]]))


;; Counter example
;; -------------------------------------------------------------

(def total-count
  (r/atom {:name 0}))


;; Blog App
;; -------------------------------------------------------------

(def articles
  (reverse (sort-by #(:id %) ; can be done properly with timestamp later, using cljs-time
                    [{:id 3
                      :title "This is article 1, This is article 1, This is article 1"
                      :content (readfile "md/one.md")
                      :topics "Clojure | ClojureScript"
                      :date "Dec 20, 2018"}
                     {:id 2
                      :title "This is article 2, This is article 2, This is article 2"
                      :content (readfile "md/two.md")
                      :topics "Programming | Logic Programming"
                      :date "Dec 18, 2018"}
                     {:id 1
                      :title "This is myfile, This is myfile, This is myfile"
                      :content (readfile "md/three.md")
                      :topics "Clojure | ClojureScript | Programming | Logic Programming"
                      :date "Dec 17, 2018"}])))


(def about (readfile "md/about.md"))

(def blog-db
  (r/atom {:articles articles
           :current-page :home
           :current-article {}
           :about about}))
