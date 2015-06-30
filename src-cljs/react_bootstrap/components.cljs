(ns react-bootstrap.components
  (:refer-clojure :exclude [time map meta])
  (:require-macros [react-bootstrap.components :refer [define-tags]])
  (:require (cljsjs.react-bootstrap)))



(define-tags
  Accordion Alert Button ButtonGroup ButtonToolbar Col CollapsableNav DropdownButton Glyphicon Grid Input Label MenuItem Modal ModalTrigger Nav Navbar NavItem OverlayTrigger Panel Popover Row Table TabbedArea TabPane Tooltip Well)
